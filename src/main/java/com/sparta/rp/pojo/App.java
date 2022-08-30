package com.sparta.rp.pojo;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            ChuckNorrisPojo chuckNorrisPojo = mapper.readValue(new URL("https://api.chucknorris.io/jokes/random"), ChuckNorrisPojo.class);
            System.out.println(Arrays.toString(chuckNorrisPojo.getClass().getDeclaredClasses()));
            JsonNode jsonNode = mapper.readTree(new URL("https://api.chucknorris.io/jokes/random"));
            System.out.println(jsonNode.getClass());
            System.out.println(jsonNode.get("value").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
