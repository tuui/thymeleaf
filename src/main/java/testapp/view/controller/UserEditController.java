package testapp.view.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
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

@Slf4j
@Controller
@SessionAttributes(types = User.class)
public class UserEditController {

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
            return "admin/user/userEdit";
        }
        userI.saveUser(user);
        status.setComplete();
        model.clear();
        return "redirect:/admin/userList";
    }

}
