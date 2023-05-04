package com.example.demo.data.requests;

import java.util.HashMap;
import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateAccountRequest {
    private String userName;
    private String createTime;
    private boolean activeStatus;

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("USER_NAME",this.userName);
        map.put("CREATE_TIME",this.createTime);
        map.put("ACTIVE_STATUS",this.activeStatus);
        return map;
    }
}
