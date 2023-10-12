package com.success.springbootk8scurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.success.springbootk8scurd.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
