package com.spice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.dao.repository.UserRepository;
import com.spice.dao.repository.entity.UserEntity;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
    
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<UserEntity> getUsers() throws Exception {
    	return userRepository.findAll();
	}
    
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable(value="id") Long id) {
		return userRepository.findOne(id) ;
    }
    
	@RequestMapping(value="/users", method = RequestMethod.POST)
    public UserEntity saveUser(@RequestBody UserEntity user) throws Exception {
		 return userRepository.save(user);
    }
    
	@RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
    public UserEntity updateUser(@PathVariable(value="id") Long id, @RequestBody UserEntity user) throws Exception {
		return userRepository.save(user);
    }
    
	@RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value="id") Long id) {
		userRepository.delete(id);
    }

}

