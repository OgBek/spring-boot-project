package com.springboot.reg.Services;

import com.springboot.reg.Dto.UserDto;
import com.springboot.reg.Entity.User;

public interface UserService {

	 User findByUsername(String username);

	 User save(UserDto userDto);
}
