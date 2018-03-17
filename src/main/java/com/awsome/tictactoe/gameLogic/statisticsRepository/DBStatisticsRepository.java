package com.awsome.tictactoe.gameLogic.statisticsRepository;

import com.awsome.tictactoe.gameLogic.GameStatus;
import com.awsome.tictactoe.util.DBConnection;

import java.sql.*;

public class DBStatisticsRepository implements IStatisticsRepository {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private int currentSessionID;
    private long connectionTime;

    public DBStatisticsRepository() throws SQLException {
        connection = DBConnection.getConnection();
        connectionTime = System.currentTimeMillis();
        statement = connection.prepareStatement("SELECT id FROM statistics ORDER BY id DESC LIMIT 1;");
        result = statement.executeQuery();
        currentSessionID = 0;
        while(result.next()){
            currentSessionID = result.getInt("id");
        }
    }

    @Override
    public void saveResult(GameStatus gameStatus) throws Exception {
        for (int i = 0; i < 2; i++) {
            try {
                switch (gameStatus) {
                    case Tie:
                        statement = connection.prepareStatement("UPDATE statistics SET ties = ties + 1 WHERE id=?;");
                        break;
                    case Player1Won:
                        statement = connection.prepareStatement("UPDATE statistics SET wins_pl1 = wins_pl1 + 1 WHERE id=?;");
                        break;
                    case Player2Won:
                        statement = connection.prepareStatement("UPDATE statistics SET wins_pl2 = wins_pl2 + 1 WHERE id=?;");
                        break;
                }
                statement.setInt(1, currentSessionID);
                statement.execute();
                break;
            } catch (SQLException e) {
                tryReconnect();
            }
        }
    }

    @Override
    public void startSession(String player1Name, String player2Name) throws Exception {
        int id_pl1,id_pl2;
        for (int i = 0; i < 2; i++) {
            try {
                id_pl1 = this.IDFromName(player1Name);
                id_pl2 = this.IDFromName(player2Name);
                statement = connection.prepareStatement("INSERT INTO statistics (player1_id, player2_id) VALUE (?,?);");
                statement.setInt(1, id_pl1);
                statement.setInt(2, id_pl2);
                statement.execute();
                break;
            } catch (SQLException e) {
                tryReconnect();
            }
        }
        currentSessionID++;
    }

    @Override
    public SessionResults getSessionResults(String playerName) throws Exception {
        int id_pl1, id_pl2;
        for (int i = 0; i < 2; i++) {
            try {
                statement = connection.prepareStatement("SELECT * FROM statistics WHERE id=?");
                statement.setInt(1, this.currentSessionID);
                result = statement.executeQuery();
                while (result.next()) {
                    id_pl1 = result.getInt("player1_id");
                    id_pl2 = result.getInt("player2_id");
                    return new SessionResults(result.getInt("wins_pl1"), result.getInt("wins_pl2"),
                            result.getInt("ties"), this.NameFromID(id_pl1),
                            this.NameFromID(id_pl2));
                }
            } catch (SQLException e) {
                tryReconnect();
                throw new SQLException(e);
            }
        }
        return null;
    }

    private String NameFromID(int id) throws SQLException {
        statement = connection.prepareStatement("SELECT username FROM users WHERE id=?;");
        statement.setInt(1, id);
        result = statement.executeQuery();
        String name = null;
        while (result.next()){
            name = result.getString("username");
        }
        result = null;
        statement = null;
        return name;
    }

    private int IDFromName(String name) throws SQLException{
        statement = connection.prepareStatement("SELECT id FROM users WHERE username=?;");
        statement.setString(1, name);
        result = statement.executeQuery();
        int id = -1;
        while (result.next()){
            id = result.getInt("id");
        }
        result = null;
        statement = null;
        return id;
    }

    private void tryReconnect() throws SQLException {
        if (System.currentTimeMillis() - this.connectionTime > 600000){
            this.connection = DBConnection.getConnection();
        }
    }
}
