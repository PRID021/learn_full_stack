package com.example.demo.data.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@AllArgsConstructor
public class UserAccount {
   @JsonProperty("id")
   private String id;
   @JsonProperty("userName")
   private String userName;
   @JsonProperty("createDate")
   private Date createDate;
   @JsonProperty("activeStatus")
   private Boolean activeStatus;

   public String toString() {
      ObjectMapper mapper = new ObjectMapper();
      String jsonString="";
      try {
         jsonString = mapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      return jsonString;
   }
}
