package com.awsome.tictactoe.controller;

import com.awsome.tictactoe.model.User;
import com.awsome.tictactoe.repositories.ConsoleUsersRepository;
import com.awsome.tictactoe.repositories.IUsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    IUsersRepository repository = new ConsoleUsersRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/log_in")
    public String logIn(@ModelAttribute User user){
        User savedUser = repository.findUser(user.getUsername());
        if (user.getPassword().equals(savedUser.getPassword())){
            return "play";
        }else {
            return "redirect:/";
        }
    }
}
