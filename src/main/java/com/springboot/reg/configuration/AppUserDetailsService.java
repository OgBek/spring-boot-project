package com.springboot.reg.configuration;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.reg.Entity.User;
import com.springboot.reg.Repo.UserRepository;


@Service
public class AppUserDetailsService  implements UserDetailsService {
	 private UserRepository userRepository;
   
     
	

	 public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	  User user = userRepository.findByUsername(username);
	  if (user == null) {
	   throw new UsernameNotFoundException("Username or Password not found");
	  }
	  return new AppUserDetails(user.getUsername(), user.getPassword(), authorities(), user.getFullname());
	 }

	 public Collection<? extends GrantedAuthority> authorities() {
	  return Arrays.asList(new SimpleGrantedAuthority("USER"));
	 }

}
