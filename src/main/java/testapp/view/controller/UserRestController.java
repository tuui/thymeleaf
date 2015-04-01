package testapp.view.controller;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import testapp.server.model.User;
import testapp.server.service.UserI;

@RestController
public class UserRestController {

	private final static Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserI userI;

	@RequestMapping("/rest/user")
	public IsUserResponse isUser(@RequestParam(value = "username") String username) {
		User user = userI.getUserByUsername(username);
		IsUserResponse response = new IsUserResponse();
		response.setUsername(username);
		if (user != null) {
			response.setRole(user.getRole());
		} else {
			response.setError("User not found");
		}
		return response;
	}

	@RequestMapping("/rest/fbuser")
	public GetFbUserResponse getFbUser(@RequestParam(value = "username") String username) {
		GetFbUserResponse response = new GetFbUserResponse();
		response.setUsername(username);

		try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.getForObject("http://graph.facebook.com/" + username, GetFbUserResponse.class);
		} catch (Exception e) {
			log.debug("user not found: ", e);
			response.setError("User not found");
		}

		return response;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GetFbUserResponse {
		private String username;
		private String name;
		private String error;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
	}

	public static class IsUserResponse {
		private String username;
		private String role;
		private String error;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
	}
}
