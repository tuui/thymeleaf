package testapp.server.model;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

	private Long id;
	@Size(min=5, max=30)
	private String username;
	@Size(min=1, max=100)
	private String firstName;
	@Size(min=1, max=100)
	private String lastName;
	private String password;
	private String email;
	private String telephone;
	
	private String role;

	public User(){
	}
	
	public User(User user) {
		super();
		this.id = user.id;
		this.username = user.username;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.password = user.password;
		this.email = user.email;
		this.telephone = user.telephone;
		this.role = user.role;
	}
}
