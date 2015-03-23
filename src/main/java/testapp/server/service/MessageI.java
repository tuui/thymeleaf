package testapp.server.service;

import java.util.List;

import testapp.server.model.Message;

public interface MessageI {
	
	List<Message> getRecievedMessages();
	
	List<Message> getSentMessages();
	
	void insertMessage(Message message);
}
