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

import com.success.springbootk8scurd.dto.UserProfileDto;
import com.success.springbootk8scurd.service.UserProfileService;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {


	@Autowired
	UserProfileService service;
	
	@GetMapping
	public List<UserProfileDto> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDto> findById(@PathVariable int id) {
		UserProfileDto dto = service.findById(id);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserProfileDto> create(@RequestBody UserProfileDto dto) {
		service.save(dto);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<UserProfileDto> update(@RequestBody UserProfileDto dto, @PathVariable int id){
		UserProfileDto oldEntity = service.findById(id);
		
		if (oldEntity != null) {
			dto.setId( oldEntity.getId());
			
			service.save(dto);		
			
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		UserProfileDto oldEntity = service.findById(id);
		
		if (oldEntity != null) {
			service.delete(id);		
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
