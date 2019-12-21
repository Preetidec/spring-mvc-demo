package com.cs.pree.demo.repositary;

import org.springframework.data.repository.CrudRepository;

import com.cs.pree.demo.modal.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String username,String password);
}
