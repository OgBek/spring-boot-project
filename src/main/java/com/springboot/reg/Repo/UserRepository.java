package com.springboot.reg.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.reg.Dto.UserDto;
import com.springboot.reg.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	 User save(UserDto userDto);
	
}
