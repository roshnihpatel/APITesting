package com.sparta.rp.boredapitestingframework.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {
    private static final String BASE_URL = "http://www.boredapi.com/api/activity/";

    public static String getConnection(){
        return BASE_URL;
    }

    public static  String getConnection(String key, String value){
        return BASE_URL + "?" + key + "=" + value;
    }
    public static  String getConnection(String key, int value){
        return BASE_URL + "?" + key + "=" + value;
    }

    private static HttpResponse<String> getResponse(){
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static int getStatusCode(){
        return getResponse().statusCode();
    }
    public static String getHeader(String key){
        return getResponse().headers().firstValue(key).orElse("Key not found");
    }
}
