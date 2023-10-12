package com.success.springbootk8scurd.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.springbootk8scurd.dto.OrderDto;
import com.success.springbootk8scurd.dto.UserProfileDto;
import com.success.springbootk8scurd.entity.UserProfile;
import com.success.springbootk8scurd.repository.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	UserProfileRepository repository;

	public List<UserProfileDto> findAll(){
		
		return repository.findAll().stream().map(entity->{
			UserProfileDto dto = new UserProfileDto(); 
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	public UserProfileDto findById(int id){
		UserProfile entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException() );
		
		List<OrderDto> OrderDtoList = entity.getOrderList().stream().map(Order->{
			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(Order, orderDto);
			return orderDto;
		}).collect(Collectors.toList());
		
		UserProfileDto dto = new UserProfileDto();
		dto.setOrderList(OrderDtoList);
		
		BeanUtils.copyProperties(entity, dto);
		
		return dto;
	}
	
	public UserProfileDto save(UserProfileDto dto) {

		UserProfile entity = new UserProfile();
		
		BeanUtils.copyProperties(dto, entity);
		
		repository.save(entity);
		
		BeanUtils.copyProperties(entity, dto);
		
		return dto;
	}
	
	public void delete(int id) {
		UserProfile entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException() );
		
		repository.deleteById(id);
	}



}
