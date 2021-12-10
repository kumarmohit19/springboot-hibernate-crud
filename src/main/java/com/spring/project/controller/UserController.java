package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.User;
import com.spring.project.sevice.userService;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private userService service;
	
	@PostMapping("user")
	public String save(@RequestBody User user) {
		service.savePerson(user);
		return "success";
	}
	
	@GetMapping("user")
	public List<User> getAllPerson(){
		return service.getPersons();
  }

  @GetMapping("user/{id}")
	public User getPerson(@PathVariable("id") int id){
		return service.getPerson(id);
  }
  
  @DeleteMapping("user/{id}")
	public String delete(@PathVariable("id") int id){
    service.deletePerson(id);
    return "success";
  }
  
  @PutMapping("user/{id}")
	public String update(@PathVariable("id") int id){
    service.updatePerson(id);
    return "success";
	}
	
}
