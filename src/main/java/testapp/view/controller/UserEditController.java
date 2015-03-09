package testapp.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import testapp.server.model.User;
import testapp.server.service.UserService;

@Controller
@SessionAttributes(types = User.class)
public class UserEditController {

	private final static Logger log = LoggerFactory.getLogger(UserEditController.class);
	
	@Autowired
	private UserService userService;
	
	//	User peab olema argumendiks, muidu tuleb: Neither BindingResult nor plain target object for bean name 'user'
	@RequestMapping({"/userEdit"})
    public String viewEditUser(final User user, final ModelMap model) {
		log.info("viewEditUsermodel-----------" + model);
//		log.info("req------------->" + req.getParameter("editUser"));
//		log.info("user..........." + user.getUsername());
		
//		User loaded = (User)model.get("user");
//		log.info("loaded..........." + loaded.getUsername());
		
//		if(!StringUtils.isEmpty(req.getParameter("editUser"))){
//			User loaded = userService.getUser(Long.valueOf(req.getParameter("editUser")));
//			log.info("id............" + loaded.getId());
//			user.setId(loaded.getId());
//			user.setUsername(loaded.getUsername());
//			user.setFirstName(loaded.getFirstName());
//			user.setLastName(loaded.getLastName());
//		}
        return "user/userEdit";
    }
	
	@RequestMapping(value="/userEdit", params={"saveUser"})
    public String saveUser(final User user, final BindingResult bindingResult, SessionStatus status) {
        if (bindingResult.hasErrors()) {
            return "userEdit";
        }
        log.info("usergetid......." + user.getId());
        userService.saveUser(user);
        status.setComplete();
        return "redirect:/userList";
    }

}
