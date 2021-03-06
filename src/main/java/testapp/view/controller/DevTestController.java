package testapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/devTest")
public class DevTestController {
	
	@RequestMapping(method = RequestMethod.GET)
    public String devTestPage() {
		return "admin/devtest/devTest";
    }
	
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String throwException() {
        throw new RuntimeException("Exception thrown on purpose!");
    }
}
