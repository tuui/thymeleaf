package testapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DevTestController {
	
	@ModelAttribute("page")
	public String module() {
		return "devTest";
	}
	
	@RequestMapping(value = "/admin/devTest", method = RequestMethod.GET)
    public String devTestPage() {
		return "admin/devtest/devTest";
    }
	
	@RequestMapping(value = "/admin/devTest/exception", method = RequestMethod.GET)
    public String throwException() {
        throw new RuntimeException("Exception thrown on purpose!");
    }
}
