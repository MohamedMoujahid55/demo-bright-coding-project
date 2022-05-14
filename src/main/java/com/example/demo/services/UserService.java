package com.example.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	public UserDto createUser(UserDto userDto);
	public UserDto getUser(String email);
	public UserDto getUserByUserId(String userId);
	public UserDto updateUser(String id, UserDto userDto);
	public void deleteUserbyId(String id);
	public List<UserDto> getUsers(int page, int limit);
}
