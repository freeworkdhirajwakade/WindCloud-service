package com.windcloud.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.windcloud.constants.CommanConstants;
import com.windcloud.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		com.windcloud.entity.User user=userService.findbyEmailIdAndStatus(username,CommanConstants.USER_STATUS_APPROVED);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		return new User(username,user.getPassword(),user.getAuthorities());
	}
}
