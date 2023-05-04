package com.example.demo.config;

import java.io.IOException;

import org.json.JSONException;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.data.utils.JsonAssets;
import com.example.demo.data.utils.ReadJsonFileToJsonObject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.json.JSONObject;

@OpenAPIDefinition
@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Bean
    public OpenAPI customOpenAPI() throws JSONException, IOException {
        ReadJsonFileToJsonObject readJsonFileToJsonObject = new ReadJsonFileToJsonObject();
        JSONObject oJsonObject = readJsonFileToJsonObject.read();
        System.out.println("oJsonObject: " + oJsonObject);
        ApiResponse badRequestApi = new ApiResponse().content(
                        new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                                new MediaType().addExamples("default",
                                        new Example().value((oJsonObject.get("badRequestResponse").toString()))
                                )))
                .description("Bad Request");
        ApiResponse internalServerErrorApi = new ApiResponse().content(
                new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                        new MediaType().addExamples("default",
                                new Example().value(oJsonObject.get("internalServerErrorResponse").toString()))
                )
        ).description("Internal Server Error");
        Components components = new Components();
        components.addResponses("badRequestApi", badRequestApi);
        components.addResponses("internalServerErrorApi", internalServerErrorApi);
        return new OpenAPI().components(components).info(new io.swagger.v3.oas.models.info.Info().title("Demo API").version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi authApi() {
        String[] paths = {"/auth/**"};
        return GroupedOpenApi.builder().group("AUTHENTICATION").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi postApi() {
        String[] paths = {"/post/**"};
        return GroupedOpenApi.builder().group("POST").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi accountApi() {
        String[] paths = {"/account/**"};
        return GroupedOpenApi.builder().group("ACCOUNT").pathsToMatch(paths)
                .build();
    }

}
