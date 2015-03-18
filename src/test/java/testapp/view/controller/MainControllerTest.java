package testapp.view.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import testapp.AbstractTestCase;

public class MainControllerTest extends AbstractTestCase {

	@Before
	public void setup() {
		// Setup Spring test in webapp-mode (same config as spring-boot)
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testRoot() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Welcome!")));
	}

	@Test
	public void testContact() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/contact")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("You can contact us!")));
	}

	@Test
	public void testLoginError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login-error")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Welcome!")));
	}
}
