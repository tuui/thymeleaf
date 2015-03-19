package testapp.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import testapp.server.model.User;
import testapp.server.service.UserI;

@Controller
@RequestMapping("/admin/userList")
public class UserListController {

	private final static Logger log = LoggerFactory.getLogger(UserListController.class);
	
	@Autowired
	private UserI userI;
	private List<User> users;
	
	@ModelAttribute("page")
	public String module() {
		return "users";
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String viewUserList(ModelMap model) {
		log.debug("viewUserList...");
		users = userI.getUsers();
		model.addAttribute("allUsers", users);
		return "admin/user/userList";
    }

	@RequestMapping(params={"editUser"}, method = RequestMethod.POST)
    public String editUser(final HttpServletRequest req, RedirectAttributes redirectAttrs) {
		log.debug("editUser...");
		final Integer rowId = Integer.valueOf(req.getParameter("editUser"));
		redirectAttrs.addFlashAttribute("user", users.get(rowId));
        return "redirect:/admin/userEdit";
    }
	
	@RequestMapping(params={"addUser"}, method = RequestMethod.POST)
    public String addUser(RedirectAttributes redirectAttrs) {
		log.debug("addUser...");
		redirectAttrs.addFlashAttribute("user", new User());
        return "redirect:/admin/userEdit";
    }
	
	/*	
	@RequestMapping(params={"editUser"}, method = RequestMethod.POST)
    public String editUser(User user, BindingResult result, final ModelMap model, final HttpServletRequest req) {
		log.info("req------------->" + req.getParameter("editUser"));
		log.info("user............" + user.getUsername());
		log.info("model-----------" + model);
//		@SuppressWarnings("unchecked")
//		List<User> users = (LinkedList<User>) model.get("allUsers");
//		log.info ("klass.............." + users.get(0).getUsername());
//		model.addAttribute("uuu", users.get(0));
		
        return "redirect:/userEdit";
	}
	
	*/
	/*@RequestMapping(value="/userList", params={"deleteUser"})
    public String deleteUser(final User user, final BindingResult bindingResult, final HttpServletRequest req) {
        final Long userId = Long.valueOf(req.getParameter("deleteUser"));
        userService.removeUser(userId);
        return "redirect:/userList";
    }*/
}
