package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.requests.LoginRequest;
import com.example.demo.data.responses.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@PostMapping("/signIn")
	@Operation(
			summary = "Sign in", 
			description = "Sign in to the application",
			tags = { "Authentication" },
			responses = {
				@ApiResponse(responseCode = "400",ref = "badRequestApi"),
				@ApiResponse(responseCode = "500",ref = "internalServerErrorApi"),
				@ApiResponse(responseCode = "200", description = "Sign in successful",
					content = @Content(
								mediaType = "application/json",
								examples = { 
										@ExampleObject(value = "{\n" + "  \"code\" : 200,\n"+ "  \"status\" : \"OK\",\n" + "  \"message\" : \"Sign in successful\"\n" + "}"
									) })
				)
			}
			)
	public ResponseEntity<?> signIn(
		@RequestBody()
		 LoginRequest loginRequest) {

			System.out.println(loginRequest.toString());
		return new ResponseEntity<>(
				new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Sign in successful"),
				HttpStatus.OK);

	}


}
