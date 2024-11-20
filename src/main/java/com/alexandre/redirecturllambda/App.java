package com.alexandre.redirecturllambda;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App implements RequestHandler<Map<String, Object>, Map<String, Object>> 
{
    private final S3Client s3Client = S3Client.builder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        String pathParameters = (String) input.get("rawPath");
        String shortUrlCode = pathParameters.replace("/", "");

        if(shortUrlCode == null || shortUrlCode.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: 'ShortUrlCode' is required");
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("projeto-lambda-shortener-url-ale")
                .key(shortUrlCode + ".json")
                .build();

        InputStream s3ObjectStream;

        try {
            s3ObjectStream = s3Client.getObject(getObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("Errp fetching URL data from S3:" + e.getMessage());
        }

        UrlData urlData;

        try {
            urlData = objectMapper.readValue(s3ObjectStream, UrlData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing URL data: " + e.getMessage());
        }

        Map<String, Object> response = new HashMap<>();

        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
    
        //URL Expired
        if(currentTimeInSeconds < urlData.getExpirationTime()) {
            response.put("statusCode", 302);
            Map<String, String> headers = new HashMap<>();
            headers.put("Location", urlData.getOriginalUrl());
            response.put("headers", headers);

            return response;
        }

        //Url valid
        response.put("statusCode", 410);
        response.put("body", "This Url has expired");

        return response;
    }
}
