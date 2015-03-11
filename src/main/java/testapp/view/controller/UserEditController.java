package testapp.view.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	private String oldPassword;
	
	//	User peab olema argumendiks, muidu tuleb: Neither BindingResult nor plain target object for bean name 'user'
	@RequestMapping({"/userEdit"})
    public String viewUserEdit(final User user) {
		log.info("viewEditUsermodel-----------" + user.getPassword());
		oldPassword = user.getPassword();
        return "user/userEdit";
    }
	
	@RequestMapping(value="/userEdit", params={"saveUser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, SessionStatus status) {	
		if(user.getPassword().isEmpty()){
			user.setPassword(oldPassword);
		}
        if (bindingResult.hasErrors()) {
            return "user/userEdit";
        }
        userService.saveUser(user);
        status.setComplete();
        return "redirect:/userList";
    }

}
