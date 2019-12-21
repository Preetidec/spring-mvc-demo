package com.cs.pree.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.pree.demo.modal.User;
import com.cs.pree.demo.services.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String hello() {
			return "this is Home page";
	}
	
  //@GetMapping("/save-user")
  @GetMapping("/saveuser")
	public String saveUser(@RequestParam String username,@RequestParam String firstname,@RequestParam String lastname, @RequestParam int age,@RequestParam String password)
   {
	User user=new User(username,firstname,lastname,age,password);
	userService.saveMyUser(user);
	return "User Save";
   }

}