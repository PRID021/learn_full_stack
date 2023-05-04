package com.example.demo.data.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AccountResponse  extends Response{
    public AccountResponse(int value, String reasonPhrase, String string, Object jsonStrings) {
        super(value, reasonPhrase, string);
        this.data = jsonStrings;
    }

    private Object data;
    
}
