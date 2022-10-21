package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	@Query("SELECT r FROM Roles r WHERE r.role = ?1")
	Roles findRolebyRole(String role);

}
