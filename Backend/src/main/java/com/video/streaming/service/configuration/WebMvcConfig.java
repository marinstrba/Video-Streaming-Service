package com.video.streaming.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// It is a security feature implemented by web browsers to restrict web pages from making requests to a different domain than the one that served the web page.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry)
    {
        // CorsRegistry is a class that provides methods to configure CORS
        corsRegistry.addMapping("/**") // basically allows user which methods to use
                .allowedOrigins("*") // allows request from every origin (example: I can only say i allow request from google.com)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // specifies which methods are allowed
                .allowedHeaders("*") // what headers can be used when making request (headers provide additional info about the request)
                .maxAge(3600); // before the request the browser send flight that asks if it is ok to use the server the browser can store this for the time of 3600 seconds
    }

}
