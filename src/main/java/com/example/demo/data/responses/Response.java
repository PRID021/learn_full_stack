package com.example.demo.data.responses;

// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private Integer code;

	private String status;

	private String message;

}
