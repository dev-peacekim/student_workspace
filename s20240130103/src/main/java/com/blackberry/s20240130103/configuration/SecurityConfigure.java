package com.blackberry.s20240130103.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfigure {
	
	
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.
				authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/**"
								+ "").permitAll()
				);
			http.csrf(csrf->csrf.disable());
		return http.build();
	}
	
}
