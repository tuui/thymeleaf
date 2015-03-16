package testapp.view.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import testapp.AbstractTestCase;
import testapp.server.model.User;

public class UserEditControllerTest extends AbstractTestCase {

	private static final String USERNAME = "test_username";
	
	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in webapp-mode (same config as spring-boot)
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testViewUserEdit() throws Exception {
		User user = new User();
		user.setUsername(USERNAME);

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/userEdit").flashAttr("user", user))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(USERNAME)));
	}

}
