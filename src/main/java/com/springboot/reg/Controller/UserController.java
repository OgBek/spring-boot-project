package com.springboot.reg.Controller;




import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.reg.Dto.UserDto;
import com.springboot.reg.Entity.User;
import com.springboot.reg.Services.UserService;


@Controller
public class UserController {
	
	@Autowired

	

	 private UserService userService;

	
	 
	

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/")
	    public ModelAndView redirectToLogin() {
	        return new ModelAndView("redirect:/login");
	    }

	@GetMapping("/home")
	   public String home() {
	    
	    return "home";
	   }

	 @GetMapping("/login")
	 public String login(Model model, UserDto userDto) {

	  model.addAttribute("user", userDto);
	  return "login";
	 }

	 @GetMapping("/register")
	 public String register(Model model, UserDto userDto) {
	  model.addAttribute("user", userDto);
	  return "register";
	 }

	 @PostMapping("/register")
	 public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
	  User user = userService.findByUsername(userDto.getUsername());
	  if (user != null) {
	   model.addAttribute("Userexist", user);
	   return "register";
	  }
	  userService.save(userDto);
	  return "redirect:/login?success";
	 }
}
