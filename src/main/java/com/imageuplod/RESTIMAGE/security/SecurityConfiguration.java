package com.imageuplod.RESTIMAGE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imageuplod.RESTIMAGE.service.RegisterService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
    @Autowired
    private RegisterService userDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();


//        http.authorizeRequests()
//        .antMatchers(HttpMethod.POST, "register/login").permitAll()
//        .antMatchers(HttpMethod.POST,"/register").permitAll()
//        .antMatchers("register/login").permitAll()
//        .anyRequest().authenticated().and().
//        exceptionHandling().and().sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //**************************
        
        http.csrf().disable().cors().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register/login").permitAll()
                .anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	System.out.println("AAA");
        auth.userDetailsService(userDetailService);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
//    	System.out.println("AAA");
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

	
}