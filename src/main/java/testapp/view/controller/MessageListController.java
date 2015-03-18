package testapp.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import testapp.server.model.Message;
import testapp.server.service.MessageI;

@Controller
@RequestMapping("/auth//messageList")
public class MessageListController {

	@Autowired
	private MessageI messageI;
	
	@ModelAttribute("page")
	public String module() {
		return "messages";
	}
	
	@ModelAttribute("allMessages")
	public List<Message> allMessages(){
		return messageI.getMessages();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewMessageList(){
		return "/auth/message/messageList";
	}
}
