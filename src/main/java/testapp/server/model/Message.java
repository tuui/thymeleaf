package testapp.server.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Message {

	private Long id;
	@Size(min=1, max=100)
	private String text;
	private Date date;
	private MessageTypeEnum type;
	private String orderNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MessageTypeEnum getType() {
		return type;
	}

	public void setType(MessageTypeEnum type) {
		this.type = type;
	}
	
	public boolean isTypeOrder(){
		return MessageTypeEnum.ORDER.equals(type);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
