package testapp.view.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import testapp.AbstractTestCase;

public class UserListControllerTest extends AbstractTestCase {

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in webapp-mode (same config as spring-boot)
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testListProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/userList")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Users list")));
	}

	@Test
	public void testAddUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/userList").param("addUser", "")).andExpect(MockMvcResultMatchers.redirectedUrl("/admin/userEdit"));
	}

	@Test
	public void testEditUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/userList"));
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/userList").param("editUser", "1"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/userEdit"));
	}
}
