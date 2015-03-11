package testapp.server.service;

import java.util.List;

import testapp.server.model.User;


public interface UserI {


	List<User> getUsers();

	User getUser(Long id);
	
	User getUserByUsername(String username);

	void saveUser(User user);

	void removeUser(Long userId);

}
