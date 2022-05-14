package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.responses.ErrorMessages;
import com.example.demo.services.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.AddressDto;
import com.example.demo.shared.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

@Service // pour injecter l'objet instancier de l'interface
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if(checkUser != null)
			throw new UserException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		
		/*UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);*/


		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		for(int i=0; i < user.getAddresses().size(); i++) {
			AddressDto address = user.getAddresses().get(i);
			address.setUser(user);
			address.setAddressId(util.generateStringId(30));
			user.getAddresses().set(i, address);
		}
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(util.generateStringId(32));

		System.out.println(userEntity.getAddresses().get(0).getCountry());

		UserEntity newUser =userRepository.save(userEntity);
		
		/* UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);*/
		
		UserDto userDto =  modelMapper.map(newUser, UserDto.class);

		return userDto;
	}

	@Override // this method helps to get the user from DB
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null)throw new UsernameNotFoundException(email);
			
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null)
			throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null)throw new UsernameNotFoundException(userId);
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)throw new UsernameNotFoundException(id);
		
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());

		UserEntity NewUser = userRepository.save(userEntity);
		UserDto newDto = new UserDto();
		
		BeanUtils.copyProperties(NewUser, newDto);

		
		return newDto;
	}

	@Override
	public void deleteUserbyId(String id) {

		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)throw new UsernameNotFoundException(id);
		
		userRepository.delete(userEntity);		
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		if(page > 0) page -= 1;
		
		List<UserDto> usersDto =  new ArrayList<>();
		
		Pageable pagebleRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage = userRepository.findAll(pagebleRequest);
		
		List<UserEntity> users = userPage.getContent();
		

		for(UserEntity userEntity : users) {
			
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			
			usersDto.add(user);
		}
		
		return usersDto;
	}

	

}
