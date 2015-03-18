package testapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Application home page and login.
 */
@Controller
public class MainController {

	private String page = "home";
	
	/* used when "page" attribute is removed, e.g. in case of login-error */
	@ModelAttribute("page")
	public String module() {
		return page;
	}

	@RequestMapping("/")
	public String root(Model model) {
		page = "home";
		model.addAttribute("page", "home");
		return "index";
	}

	/** Login form with error. */
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		page = "contact";
		model.addAttribute("page", "contact");
		return "public/contact";
	}
}
