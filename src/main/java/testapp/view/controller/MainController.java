package testapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    /** Login form with error. */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }
    
    @RequestMapping("/contact")
    public String contact() {
        return "public/contact";
    }
}
