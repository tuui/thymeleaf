package testapp.server.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import testapp.server.model.User;

@Repository
public class UserRepository {

	private Map<Long,User> users;

	public UserRepository() {
		super();
		
		users = new LinkedHashMap<>();
		
		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("admin");
		user1.setFirstName("Site");
		user1.setLastName("Administrator");
		user1.setPassword("admin");
		user1.setEmail("admin@site.com");
		user1.setTelephone("55 66 77 88");
		user1.setRole("ROLE_ADMIN");
		users.put(user1.getId(), user1);
		
		User user2 = new User();
		user2.setId(2L);
		user2.setUsername("martin");
		user2.setFirstName("Martin");
		user2.setLastName("T");
		user2.setPassword("martin");
		user2.setTelephone("500 600");
		user2.setRole("ROLE_USER");
		users.put(user2.getId(), user2);
	}
	
	public List<User> getUsers() {
        return new LinkedList<User>(this.users.values());
    }
	
	public User getUser(Long id){
		return users.get(id);
	}
	
	public User getUserByUsername(String username){
		for(User user : users.values()){
			if(user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}
	
	public void addUser(User user){
		user.setId(getUserMaxId() + 1L);
		users.put(user.getId(), user);
	}
	
	public void updateUser(User user){
		users.replace(user.getId(), user);
	}
	
	public void removeUser(Long userId){
		users.remove(userId);
	}
	
	private Long getUserMaxId(){
		Long maxId = 1L;
		for(Long id : users.keySet()){
			if(id > maxId){
				maxId = id;
			}
		}
		return maxId;
	}
}
