package com.windcloud.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> 
{
	@Query("SELECT otp FROM OneTimePassword otp WHERE otp.emailId = ?1")
	public OneTimePassword findByEmail(String emailId);
}
