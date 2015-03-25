package testapp.view.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import testapp.server.model.Message;
import testapp.server.model.MessageTypeEnum;
import testapp.server.service.MessageI;


@Controller
@RequestMapping("/auth/messageList")
public class MessageListController {

	private final static Logger log = LoggerFactory.getLogger(MessageListController.class);
	private final static String PATH = "/auth/message/messageList";
	
	@Autowired
	private MessageI messageI;
	private String tab = "tab1";
	
	@ModelAttribute("page")
	public String module() {
		return "messages";
	}
	
	@ModelAttribute("tab")
	public String tab() {
		return tab;
	}
	
	@ModelAttribute("recievedMessages")
	public List<Message> getRecievedMessages(){
		log.debug("getRecievedMessages........");
		return messageI.getRecievedMessages();
	}
	
	@ModelAttribute("sentMessages")
	public List<Message> getSendMessages(){
		log.debug("getSentMessages........");
		return messageI.getSentMessages();
	}
	
	@ModelAttribute("allMessageTypes")
	public List<MessageTypeEnum> getAllMessageTypes(){
		log.debug("getAllMessageTypes........");
		return Arrays.asList(MessageTypeEnum.GENERAL, MessageTypeEnum.ORDER);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewMessages(Model model){
		tab = "tab1";
		model.addAttribute("tab", tab);
		return PATH;
	}
	
	@RequestMapping(value = "/{tabId}", method = RequestMethod.GET)
	public String tab(@PathVariable("tabId") int tabId, Model model, Message message){
		if(tabId == 2){
			tab = "tab2";
		} else if (tabId == 3){
			tab = "tab3";
		} else {
			tab = "tab1";
		}
		model.addAttribute("tab", tab);
		return PATH;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String post(Message message, BindingResult bindingResult, Model model) {	
		return PATH;
    }
	
	@RequestMapping(params={"sendMessage"}, method = RequestMethod.POST)
    public String saveUser(@Valid Message message, BindingResult bindingResult, Model model) {	
        if (bindingResult.hasErrors()) {
            return PATH;
        }
        message.setDate(new Date());
        messageI.insertMessage(message);
 
        tab = "tab2";
        model.addAttribute("tab", tab);
        return PATH;
    }
}
