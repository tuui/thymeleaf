package testapp.server.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import testapp.server.model.Message;

@Repository
public class MessageRepository {

	private final String format = "dd.MM.yyyy HH:mm";
	private List<Message> recievedMessages;
	private List<Message> sentMessages = new LinkedList<>();
	
	public MessageRepository(){
		generateRecievedMessages();
	}
	
	public List<Message> getRecievedMessages(){
		return recievedMessages;
	}
	
	public List<Message> getSentMessages(){
		return sentMessages;
	} 
	
	public void insertMessage(Message message){
		sentMessages.add(message);
	}
	
	private void generateRecievedMessages(){
		recievedMessages = new LinkedList<>();
		
		Message m1 = new Message();
		m1.setText("Your order has been placed");
		m1.setDate(getDate("08.03.2015 14:40"));
		recievedMessages.add(m1);
		
		Message m2 = new Message();
		m2.setText("Deposit has been paid");
		m2.setDate(getDate("08.03.2015 16:15"));
		recievedMessages.add(m2);
		
		Message m3 = new Message();
		m3.setText("Your order has been shipped");
		m3.setDate(getDate("09.03.2015 09:21"));
		recievedMessages.add(m3);
		
		Message m4 = new Message();
		m4.setText("Your order has arrived");
		m4.setDate(getDate("12.03.2015 13:10"));
		recievedMessages.add(m4);
	}
	
	private Date getDate(String date){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException pe) {
			
		}
		return null;
	}
}
