package com.gl.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.studentmanagement.service.StudentAppUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
public class StudentWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	// Customizing authentication
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		AuthenticationProvider authenticationProvider = customAuthenticationProvider();
		auth.authenticationProvider(authenticationProvider);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        .antMatchers("/", "/student/save", "/student/begin-add", "/students/403")
				.hasAnyAuthority("ADMIN_USER", "NORMAL_USER")

			 	.antMatchers("/student/begin-update", "/student/delete")
			 	.hasAuthority("ADMIN_USER")
			 	.anyRequest()
				.authenticated().and()
				.formLogin()
				.loginProcessingUrl("/login").successForwardUrl("/students/list")
				.permitAll().and()
				.logout().logoutSuccessUrl("/login").permitAll().and()
				.exceptionHandling()
				.accessDeniedPage("/students/403").and()
				.cors().and().csrf().disable();

	}

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new StudentAppUserDetailsService();
	}

	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean

	public DaoAuthenticationProvider customAuthenticationProvider() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();

		daoProvider.setUserDetailsService(customUserDetailsService());
		daoProvider.setPasswordEncoder(bcryptPasswordEncoder());

		return daoProvider;
	}

}
