package controller;

import model.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    Link x[][] = new Link[][]{
            {new Link("https://data1.cupsell.pl/upload/generator/70522/640x420/1196166_print-trimmed-1.png?resize=max_sizes&key=55f9a22768eed085006592c1174c0235"),null,null},
            {null,null,null},
            {null,null,null}};

    @GetMapping("/")
    public String index() {

        return "index";
    }


    @GetMapping("/play")
    public String play() {

        return "play";
    }

}
