package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findbyEmailId(String email);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.status=?2")
	User findbyEmailIdAndStatus(String email,String status);
	
	@Query("SELECT u FROM User u WHERE u.status = ?1")
	User findUserByStatus(String status);
	

}
