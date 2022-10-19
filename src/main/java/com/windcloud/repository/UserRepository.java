package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.windcloud.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
