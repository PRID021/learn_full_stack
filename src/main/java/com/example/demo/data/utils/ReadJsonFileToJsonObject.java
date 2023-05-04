package com.example.demo.data.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class ReadJsonFileToJsonObject {
    public JSONObject  read() throws IOException{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("openapi/response.json");
        if (inputStream == null) {
            throw new FileNotFoundException("File not found at path: openapi/response.json");
        }
        String jsonString = IOUtils.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return new JSONObject(jsonString);
        
    }
}