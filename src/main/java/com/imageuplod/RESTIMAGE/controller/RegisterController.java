package com.imageuplod.RESTIMAGE.controller;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imageuplod.RESTIMAGE.entity.UserRegister;
import com.imageuplod.RESTIMAGE.exceptions.RegisterException;
import com.imageuplod.RESTIMAGE.request.AuthenticationRequest;
import com.imageuplod.RESTIMAGE.request.UpdateUserRequest;
import com.imageuplod.RESTIMAGE.response.AuthenticationResponse;
import com.imageuplod.RESTIMAGE.service.RegisterService;
import com.imageuplod.RESTIMAGE.utils.JwtUtil;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	@Autowired
	private JwtUtil jwtUtil;
	
	
    @Autowired
    AuthenticationManager authenticationManager;
    
	@PostMapping
	public ResponseEntity<UserRegister> registerUser(@Valid @RequestBody UserRegister userRegister)
	{
		System.out.println(userRegister.getPassword());
		UserRegister registeredUser=registerService.SaveRegister(userRegister);
		
		return new ResponseEntity<UserRegister>(registeredUser,HttpStatus.CREATED);
	}
    
	@GetMapping("/{userID}")
	public UserRegister getUser(@PathVariable("userID") String userID)
	{
		UserRegister userRegister=registerService.getUser(userID);
		return userRegister;
	}

	
	@PutMapping("/{userID}")
	public ResponseEntity<UserRegister> updateUser(@PathVariable("userID") String userID,@Valid @RequestBody UpdateUserRequest updateUserRequest)
	{
		UserRegister updatedUser=registerService.updateUser(userID, updateUserRequest);

		return new ResponseEntity<UserRegister>(updatedUser,HttpStatus.OK);
	}

	@DeleteMapping("/{userID}")
	public String deleteUser(@PathVariable("userID") String userID)
	{
		return "";
	}
	
    @PostMapping(value="/login")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
        	
//        	System.out.println("A"+authenticationRequest.getEmail());
//        	System.out.println("B"+authenticationRequest.getPassword());
        	
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    System.out.println("B");
}catch(Exception e)
{
    e.printStackTrace();
    throw new RegisterException("Incorrect Username or Password !!!");
}
//        System.out.println("C");
    	
final UserRegister userDetails=registerService.getUserByMail(authenticationRequest.getEmail());
final String jwt= jwtUtil.generateToken(userDetails);
System.out.println("D");

return  ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails.getUserID()));

    }
    
    public void mm()
    {
//    HttpRequest request = HttpRequest.newBuilder()
//	.uri(URI.create("https://movie-database-imdb-alternative.p.rapidapi.com/?s=Avengers%20Endgame&r=json&page=1"))
//	.header("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
//	.header("x-rapidapi-key", "3cb0de313fmshd4586ca6e16a90bp1f0910jsn0841bd76cd66")
//	.method("GET", HttpRequest.BodyPublishers.noBody())
//	.build();
//HttpResponse<String> response = HttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());
    }
   
}
