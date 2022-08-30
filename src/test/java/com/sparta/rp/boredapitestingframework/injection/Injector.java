package com.sparta.rp.boredapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.rp.boredapitestingframework.dto.ActivityDTO;

import java.io.IOException;
import java.net.URL;

public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ActivityDTO injectActivityDTO(String path) {
        ActivityDTO dto = new ActivityDTO();
        try {
            dto = mapper.readValue(new URL(path), ActivityDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }
}
