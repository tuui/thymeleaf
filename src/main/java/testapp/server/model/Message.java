package testapp.server.model;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Message {

	private Long id;
	@Size(min=1, max=100)
	private String text;
	private Date date;
	private MessageTypeEnum type;
	private String orderNo;
	
	public boolean isTypeOrder(){
		return MessageTypeEnum.ORDER.equals(type);
	}


}
