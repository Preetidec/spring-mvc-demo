package com.cs.pree.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.pree.demo.modal.User;
import com.cs.pree.demo.services.UserService;

@Controller

public class ApplicationController {
      
	@Autowired 
    private UserService userservice;
      @RequestMapping("/welcome")
      public String Welcome(HttpServletRequest req) {
    	  req.setAttribute("mode", "MODE_HOME");
    	  return "welcomepage" ;
      }
      
      @RequestMapping("/register")
 public String registration(HttpServletRequest req) 
      {
	 req.setAttribute("mode", "MODE_REGISTER");
	 return "welcomepage";
      }     

      @PostMapping("/save-user")
      public String registerUser(@ModelAttribute User user,BindingResult bindingResult,HttpServletRequest request) 
      {
    	  userservice.saveMyUser(user);
    	  request.setAttribute("mode", "MODE_HOME");
    	return "welcomepage";  
      }

      @GetMapping("/show-users")
      public String showAllUsers(HttpServletRequest req) 
      {
    	  req.setAttribute("users",userservice.showAllUsers());
    	  req.setAttribute("mode", "ALL_USERS");
    	  return "welcomepage";
      }
      
      @RequestMapping("/delete-user")
      //@GetMapping("/show-users")
      public String deleteUser(@RequestParam int id,HttpServletRequest req) 
      {
    	  userservice.deleteMyUser(id);
    	  req.setAttribute("users",userservice.showAllUsers());
    	  
    	  req.setAttribute("mode", "ALL_USERS");
    	  return "welcomepage";
      }
      
      @RequestMapping("/edit-user") 
      public String editUser(@RequestParam int id,HttpServletRequest req)
      {
    	
    	  req.setAttribute("users",userservice.editUser(id));
    	  req.setAttribute("mode", "MODE_UPDATE");
    	  return "welcomepage";
      }
      
      
      @RequestMapping("/login")
      public String login(HttpServletRequest req) 
      {
    	  req.setAttribute("mode", "MODE_LOGIN");
    	  return "welcomepage";
      }
      
      @RequestMapping("/login-user")
      public String loginUser(@ModelAttribute User user,HttpServletRequest req)
      {
    	if(userservice.findByUAndP(user.getUsername(),user.getPassword())!=null) 
    	{
    	  return "homepage";
    	}
    	else {
    		req.setAttribute("error", "invalid username and password");
    		req.setAttribute("mode", "MODE_LOGIN");
      	  return "welcomepage";
    	}
      }
}
