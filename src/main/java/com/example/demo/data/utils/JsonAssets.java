package com.example.demo.data.utils;

public class JsonAssets {
    static String sourcePath = "src/main/resources/openapi";
    public static String response = String.format( "%1$s%2$s", sourcePath,"/response.json");

    public static final String CREATE_NEW_USER_ACCOUNT_SUCCESS_EXAMPLE = "{\n" + "  \"code\" : 200,\n"+ "  \"status\" : \"OK\",\n" + "  \"message\" : \"Update post successful\"\n" + "}";
    public static final String CREATE_NEW_USER_ACCOUNT_REQUEST_ACCOUNT = "{\n" + "  \"userName\" : \"PHẠM ĐỨC HOÀNG\"," + "  \n\"createTime\" : \"20-12-2000\"," +"  \n\"activeStatus\" : true" +"\n}";
}
