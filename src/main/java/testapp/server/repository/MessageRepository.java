package testapp.server.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import testapp.server.model.Message;

@Repository
public class MessageRepository {

	public List<Message> getMessages(){
		List<Message> messages = new LinkedList<>();
		
		Message m1 = new Message();
		m1.setText("Your order has been shipped");
		messages.add(m1);
		
		Message m2 = new Message();
		m2.setText("Your order has arrived");
		messages.add(m2);
		
		return messages;
	}
	
}
