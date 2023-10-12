package com.success.springbootk8scurd.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.success.springbootk8scurd.dto.OrderDto;
import com.success.springbootk8scurd.dto.ProductDto;
import com.success.springbootk8scurd.dto.UserProfileDto;
import com.success.springbootk8scurd.entity.Order;
import com.success.springbootk8scurd.entity.UserProfile;
import com.success.springbootk8scurd.repository.OrderRepository;


@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	//@Autowired
	//RestTemplate restTemplate;

	public List<OrderDto> findAllByUserProfileId(int userProfileId){
		
		return repository.findAllByUserProfileId(userProfileId).stream().map(entity->{
			OrderDto dto = new OrderDto(); 
			BeanUtils.copyProperties(entity, dto);
			
			UserProfile userProfile = entity.getUserProfile();
			UserProfileDto userProfileDto = new UserProfileDto();
			BeanUtils.copyProperties(userProfile, userProfileDto);
			
			dto.setUserProfile(userProfileDto);
			
			return dto;
		}).collect(Collectors.toList());
	}
	
//	@CircuitBreaker(name="product", fallbackMethod="findByIdFallback")
	public OrderDto findById(int id){
		Order entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException() );
		
		UserProfile userProfile = entity.getUserProfile();
		
		OrderDto dto = new OrderDto();		
		BeanUtils.copyProperties(entity, dto);
		
		UserProfileDto userProfileDto = new UserProfileDto();
		BeanUtils.copyProperties(userProfile, userProfileDto);
		
		dto.setUserProfile(userProfileDto);
		
		//dto.setProductList(getProductDtoList(entity));
		
		return dto;
	}
	
	/*
	//@CircuitBreaker(name="product", fallbackMethod="getProductDtoListFallback")
	public List<ProductDto> getProductDtoList(Order order) {

		String uri = "lb://PRODUCT-SERVICE/products";
		
		List<String> productList = order.getProductList();
		
		String urlTemplate = UriComponentsBuilder.fromUriString(uri)
								.queryParam("number", productList) .encode().toUriString();
		

		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
						urlTemplate, 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List<ProductDto>>() {});
  
		List<ProductDto> productDtoList = response.getBody();
		
		return productDtoList;
	}
	*/
	public OrderDto findByIdFallback(int id, RuntimeException runtimeException) {
		Order entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException() );
		
		UserProfile userProfile = entity.getUserProfile();
		
		OrderDto dto = new OrderDto();		
		BeanUtils.copyProperties(entity, dto);
		
		UserProfileDto userProfileDto = new UserProfileDto();
		BeanUtils.copyProperties(userProfile, userProfileDto);
		
		dto.setUserProfile(userProfileDto);
		
		return dto;
	}
	
	public OrderDto save(OrderDto dto) {

		Order entity = new Order();
		UserProfile userProfile = new UserProfile();
		
		BeanUtils.copyProperties(dto, entity);
		BeanUtils.copyProperties(dto.getUserProfile(), userProfile);
		
		entity.setUserProfile(userProfile);
		
		repository.save(entity);
		
		dto.setId(entity.getId());
		
		return dto;
	}
	
	public void delete(int id) {
		Order entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException() );
		
		repository.deleteById(id);
	}



}
