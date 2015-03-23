package testapp.server.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Message {

	private Long id;
	@Size(min=1, max=100)
	private String text;
	private Date date;

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
}
