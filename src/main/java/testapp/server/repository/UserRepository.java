package testapp.server.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import testapp.server.model.User;

@Repository
public class UserRepository {

	private Map<Long,User> users;

	public UserRepository() {
		
		users = new LinkedHashMap<>();
		
		generateUser("admin", "Site", "Administrator", "admin", "admin@site.com", "55 66 77 88", "ROLE_ADMIN");
		generateUser("martin", "Martin", "T", "martin", null, "50 06 00 43", "ROLE_USER");
		for(int i=1; i<=30; i++){
			generateUser("user" + i, "Test" + i, "User" + i, "password", "user" + i + "@site.com", "55 43 55 " + StringUtils.leftPad(i+"", 2, "0"), "ROLE_USER");
		}
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
	
	private Long getUserMaxId(){
		Long maxId = 1L;
		for(Long id : users.keySet()){
			if(id > maxId){
				maxId = id;
			}
		}
		return maxId;
	}
	
	private void generateUser(String uname, String fname, String lname, String pw, String email, String tel, String role){
		User user = new User();
		user.setUsername(uname);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setPassword(pw);
		user.setEmail(email);
		user.setTelephone(tel);
		user.setRole(role);
		addUser(user);
	}
}
