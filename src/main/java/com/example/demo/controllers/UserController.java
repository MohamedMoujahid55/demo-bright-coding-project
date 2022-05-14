package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.UserException;
import com.example.demo.requests.UserRequest;
import com.example.demo.responses.ErrorMessages;
import com.example.demo.responses.UserResponse;
import com.example.demo.services.UserService;
import com.example.demo.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // localhost:8090/users
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = new UserDto();
		userDto = userService.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public List<UserResponse> getAllUsers(@RequestParam(value="page", defaultValue="1") int page,
										  @RequestParam(value="limit", defaultValue="3")int limit) {
		
		List<UserResponse> usersResponse = new ArrayList<>();
		
		List<UserDto> usersDto = userService.getUsers(page, limit);
		
		for(UserDto userDto : usersDto) {
			
			UserResponse user = new UserResponse();
			BeanUtils.copyProperties(userDto, user);
			
			usersResponse.add(user);
		}
		return usersResponse;
	}
	
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		
		if(userRequest.getFirstname().isEmpty()) 
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if(userRequest.getLastname().isEmpty()) 
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		// presentation layer

		/*UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);*/

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);


		// service layer
		UserDto createUser = userService.createUser(userDto);


		// response
		//UserResponse userResponse = new UserResponse();
		//BeanUtils.copyProperties(createUser, userResponse);
		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);

		//allows to return the status 204
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

		// presentation layer
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// service layer
		UserDto updateUser = userService.updateUser(id, userDto);

		// response
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity deleteUser(@PathVariable String id) {
		
		
		userService.deleteUserbyId(id);
		
		//allows to return the status 204
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
