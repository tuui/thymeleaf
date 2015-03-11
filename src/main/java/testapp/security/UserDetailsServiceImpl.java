package testapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import testapp.server.model.User;
import testapp.server.service.UserI;

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserI userI;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    User user = userI.getUserByUsername(username);
	    
	    if (user == null) {
	        throw new UsernameNotFoundException("Invalid username/password.");
	    }
	    Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());
	    
	    return new UserPrincipal(user.getUsername(), user.getPassword(), authorities);
	}

	public void setUserI(UserI userI) {
		this.userI = userI;
	}
}
