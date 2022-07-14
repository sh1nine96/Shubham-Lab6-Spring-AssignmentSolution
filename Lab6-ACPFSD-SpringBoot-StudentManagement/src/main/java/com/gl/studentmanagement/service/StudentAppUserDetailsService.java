package com.gl.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.gl.studentmanagement.entity.User;
import com.gl.studentmanagement.repository.UserRepository;
import com.gl.studentmanagement.security.MyCustomUserDetails;

@Repository
public class StudentAppUserDetailsService
    implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) 
    		throws UsernameNotFoundException {       
    	User userEntity = userRepository.getUserByUsername(username);
    	if(userEntity == null) {
    		
    		//throws Exception
    		throw new UsernameNotFoundException("Could not find user");
    	}
        return new MyCustomUserDetails(userEntity);
    }

}
