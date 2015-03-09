package testapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Application home page and login.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }
}
