package com.demo.backend.service;

import com.demo.backend.dto.RegisterUserDto;
import com.demo.backend.dto.UserDTO;
import com.demo.backend.entity.User;
import com.demo.backend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
	private PasswordEncoder bcryptEncoder;

    @Override
    public void registerUser(RegisterUserDto userDto) {

        User user = mapper.map(userDto, User.class);
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

	@Override
	public List<UserDTO> listOfUsre() {
		
		List<User> users = userRepository.findAll();
		return users.stream().map(entity -> mapper.map(entity, UserDTO.class)).collect(Collectors.toList());
	}
}
