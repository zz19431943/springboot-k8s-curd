package com.success.springbootk8scurd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.success.springbootk8scurd.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	public List<Order> findAllByUserProfileId(int userProfileId);
}
