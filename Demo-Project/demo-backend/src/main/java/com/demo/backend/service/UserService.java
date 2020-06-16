package com.demo.backend.service;

import java.util.List;

import com.demo.backend.dto.RegisterUserDto;
import com.demo.backend.dto.UserDTO;

public interface UserService {

    void registerUser(RegisterUserDto userDto);

	List<UserDTO> listOfUsre();
}
