package testapp.server.service;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import testapp.AbstractTestCase;
import testapp.server.model.User;
import testapp.server.repository.UserRepository;

public class UserServiceTest extends AbstractTestCase{

	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before
	public void setup() {
		 // Process mock annotations
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUsers() throws Exception {
		
		User user = new User();
		user.setUsername("test_username");
		
		List<User> users = new LinkedList<>();
		users.add(user);
		
		Mockito.when(userRepository.getUsers()).thenReturn(users);
		
		Assert.assertEquals(userService.getUsers().get(0).getUsername(),"test_username");
	}
}
