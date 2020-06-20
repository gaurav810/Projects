package com.demo.backend.controller;

import com.demo.backend.dto.RegisterUserDto;
import com.demo.backend.dto.UserDTO;
import com.demo.backend.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterUserDto registerUser(@RequestBody RegisterUserDto userDTO) {

        userService.registerUser(userDTO);
        return userDTO;
    }
    
    @GetMapping("/users")
    public List<UserDTO> listOfUsre() {
    	
    	List<UserDTO> users = userService.listOfUsre();
    	return users;
    }
}
