package testapp.view.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import testapp.server.model.User;
import testapp.server.service.UserI;

@Controller
@SessionAttributes(types = User.class)
public class UserEditController {

	private final static Logger log = LoggerFactory.getLogger(UserEditController.class);
	private final static List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
	
	@Autowired
	private UserI userI;
	
	private String oldPassword;
		
	@ModelAttribute("allRoles")
	public List<String> getAllRoles(){
		return roles;
	}
	
	//	User peab olema argumendiks, muidu tuleb: Neither BindingResult nor plain target object for bean name 'user'
	@RequestMapping({"/admin/userEdit"})
    public String viewUserEdit(final User user, Model model) {
		log.debug("viewEditUsermodel...id={}", user.getId());
		oldPassword = user.getPassword();
		model.addAttribute("page", "users");
        return "admin/user/userEdit";
    }
	
	@RequestMapping(value="/admin/userEdit", params={"saveUser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, SessionStatus status, ModelMap model) {	
		if(user.getPassword().isEmpty()){
			user.setPassword(oldPassword);
		}
		
		if(StringUtils.isEmpty(user.getPassword())){
			bindingResult.rejectValue("password", "Empty", "Password cannot be empty.");
		}

        if (bindingResult.hasErrors() || !roles.contains(user.getRole())) {
        	model.addAttribute("page", "users");
            return "admin/user/userEdit";
        }
        userI.saveUser(user);
        status.setComplete();
        model.clear();
        return "redirect:/admin/userList";
    }

}
