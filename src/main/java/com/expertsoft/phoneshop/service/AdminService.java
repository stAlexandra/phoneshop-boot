package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.dto.UserDto;
import com.expertsoft.phoneshop.facade.populators.UserDtoPopulator;
import com.expertsoft.phoneshop.persistence.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserDtoPopulator userDtoPopulator;

	public Page<UserDto> getAllRegisteredUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(userDtoPopulator);
	}
}
