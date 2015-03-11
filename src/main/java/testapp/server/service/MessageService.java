package testapp.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import testapp.server.model.Message;
import testapp.server.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> getMessages(){
		return messageRepository.getMessages();
	}
}
