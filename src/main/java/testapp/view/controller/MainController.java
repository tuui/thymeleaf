package testapp.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Application home page and login.
 */
@Controller
public class MainController {
	private final static Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/")
	public String root(Model model) {
		return "index";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		return "public/contact";
	}
	
	/** Login form with error. */
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}
	
	/** Error page. */
    @RequestMapping("/error")
    public String error(HttpServletRequest request, RedirectAttributes redirectAttrs) {
    	Integer errorCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
    	log.error("Error {} occured", errorCode);
    	
    	if(errorCode == null || errorCode != 500){
    		return "redirect:/";
    	}
    	
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = throwable != null ? ExceptionUtils.getStackTrace(throwable) : "Throwable is null!";
        redirectAttrs.addFlashAttribute("stacktrace", errorMessage);        
        return "redirect:/stacktrace";
    }
    
    @RequestMapping("/stacktrace")
    public String showStacktrace(@ModelAttribute("stacktrace") String stacktrace, Model model) {
        model.addAttribute("errorMessage", stacktrace);
        return "error";
    }
    
}
