package testapp.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import testapp.server.model.Message;
import testapp.server.repository.MessageRepository;

@Service
public class MessageService implements MessageI{

	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> getRecievedMessages(){
		return messageRepository.getRecievedMessages();
	}
	
	public List<Message> getSentMessages(){
		return messageRepository.getSentMessages();
	}
	
	public void insertMessage(Message message){
		messageRepository.insertMessage(message);
	}
}
