package com.success.springbootk8scurd.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.success.springbootk8scurd.dto.OrderDto;
import com.success.springbootk8scurd.dto.UserProfileDto;
import com.success.springbootk8scurd.service.OrderService;
import com.success.springbootk8scurd.service.UserProfileService;

@RestController
@RequestMapping("/user-profiles/{userProfileId}/orders")
public class OrderController {


	@Autowired
	OrderService service;
	
	@Autowired
	UserProfileService userProfileService;
	
	@GetMapping
	public List<OrderDto> findAll(@PathVariable int userProfileId){
		return service.findAllByUserProfileId(userProfileId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> findById(@PathVariable int id) {
		OrderDto dto = service.findById(id);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> create(@PathVariable int userProfileId, @RequestBody OrderDto dto) {
		UserProfileDto userProfileDto = userProfileService.findById(userProfileId);
		dto.setUserProfile(userProfileDto);
		
		service.save(dto);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<OrderDto> update(@RequestBody OrderDto dto, @PathVariable int id){
		OrderDto oldEntity = service.findById(id);
		
		if (oldEntity != null) {
			dto.setId( oldEntity.getId());
			dto.setUserProfile(oldEntity.getUserProfile());
			
			service.save(dto);		
			
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		OrderDto oldEntity = service.findById(id);
		
		if (oldEntity != null) {
			service.delete(id);		
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}



}
