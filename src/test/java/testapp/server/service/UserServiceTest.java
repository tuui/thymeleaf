package testapp.server.service;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import testapp.AbstractTestCase;
import testapp.server.model.User;
import testapp.server.repository.UserRepository;

public class UserServiceTest extends AbstractTestCase{

	private static final Long USER_ID = 123L;
	private static final String USERNAME = "test_username";
	
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
		List<User> users = new LinkedList<>();
		users.add(createUser(USER_ID));
		
		Mockito.when(userRepository.getUsers()).thenReturn(users);
		Assert.assertEquals(userService.getUsers().get(0).getUsername(),USERNAME);
	}
	
	@Test
	public void testGetUser() throws Exception {
	    Mockito.doReturn(createUser(USER_ID)).when(userRepository).getUser(USER_ID);
	    User user = userService.getUser(USER_ID);
	    Assert.assertEquals(user.getId(),USER_ID);
	}
	
	@Test
	public void testGetUserByUsername() throws Exception {
	    Mockito.doReturn(createUser(USER_ID)).when(userRepository).getUserByUsername(USERNAME);
	    User user = userService.getUserByUsername(USERNAME);
	    Assert.assertEquals(user.getId(),USER_ID);
	}
	
	@Test
	public void testSaveUser() throws Exception {
		//	verify that addUser is called with not id only once
		User userAdd = createUser(null);
		userService.saveUser(userAdd);
		Mockito.verify(userRepository, Mockito.times(1)).addUser(userAdd);
		
		//	verify that updateUser is called with id specified and user is same
		final ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		User userUpdate = createUser(USER_ID);
		userService.saveUser(userUpdate);
		Mockito.verify(userRepository).updateUser(userCaptor.capture());
		Assert.assertTrue(userCaptor.getValue().equals(userUpdate));
	}
	
	private User createUser(Long id){
		User user = new User();
		user.setId(id);
		user.setUsername(USERNAME);
		user.setFirstName("test_firstname");
		user.setLastName("test_lastname");
		return user;
	}
}
