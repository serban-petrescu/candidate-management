package ro.msg.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.cm.model.CustomUserDetails;
import ro.msg.cm.model.Users;
import ro.msg.cm.repository.UserRepository;

import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUser = userRepository.findByUsername(username);

		optionalUser
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optionalUser
				.map(CustomUserDetails::new).get();
	}
}
