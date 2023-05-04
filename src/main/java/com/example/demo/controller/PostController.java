package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.requests.PostRequest;
import com.example.demo.data.responses.Response;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;




@RestController
@RequestMapping("/post")

public class PostController  {
    @PostMapping("/createPost")
    @Operation(
                summary = "Create Post Service",
                description = "Create post",
                tags = { "Post" },
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
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        return new ResponseEntity<>(
            new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Create post successful"),
            HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(
        description = "Update post",
        tags = { "Post" },
        summary = "Update post service",
        responses = {
            @ApiResponse(responseCode = "400",ref = "badRequestApi"),
            @ApiResponse(responseCode = "500",ref = "internalServerErrorApi"),
            @ApiResponse(responseCode = "200", description = "Update post successful",
                content = @Content(
                            mediaType = "application/json",
                            examples = { 
                                    @ExampleObject(value = "{\n" + "  \"code\" : 200,\n"+ "  \"status\" : \"OK\",\n" + "  \"message\" : \"Update post successful\"\n" + "}"
                                ) })
            )
        }
)
    public ResponseEntity<?> updatePost(@PathVariable Integer id  ,@RequestBody PostRequest postRequest){
        return new ResponseEntity<>(
            new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Sign in successful"),
            HttpStatus.OK);
    }


    
}
