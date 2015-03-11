package testapp.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import testapp.server.model.User;
import testapp.server.repository.UserRepository;

@Service
public class UserService implements UserI{

	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	public User getUser(Long id) {
		return userRepository.getUser(id);
	}
	
	public User getUserByUsername(String username){
		return userRepository.getUserByUsername(username);
	}

	public void saveUser(User user) {
		if (user.getId() != null) {
			userRepository.updateUser(user);
		} else {
			userRepository.addUser(user);
		}
	}

	public void removeUser(Long userId) {
		userRepository.removeUser(userId);
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
