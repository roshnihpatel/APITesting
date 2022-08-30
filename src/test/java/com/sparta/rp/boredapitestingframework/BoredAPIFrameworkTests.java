package com.sparta.rp.boredapitestingframework;

import com.sparta.rp.boredapitestingframework.connection.ConnectionManager;
import com.sparta.rp.boredapitestingframework.dto.ActivityDTO;
import com.sparta.rp.boredapitestingframework.injection.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoredAPIFrameworkTests {
    private static ActivityDTO dto;
    private static int statusCode;
    @BeforeAll
    static void initAll() {
        dto = Injector.injectActivityDTO(ConnectionManager.getConnection());
        statusCode = ConnectionManager.getStatusCode();
    }

    @Test
    @DisplayName("Check status code is 200")
    void checkStatusCodeIs200() {
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("Check that the server software is Cowboy")
    void checkThatTheServerSoftwareIsCowboy() {
        Assertions.assertEquals("Cowboy", ConnectionManager.getHeader("server"));
    }

    @Test
    @DisplayName("Check that the activity is not blank")
    void checkThatTheActivityIsNotBlank() {
        Assertions.assertTrue(dto.hasActivity());
    }

    @Test
    @DisplayName("Check that there at least zero participants")
    void checkThatThereAtLeastZeroParticipants() {
        Assertions.assertTrue(dto.hasAPositiveNumberOfParticipants());
    }

    @Test
    @DisplayName("Check that the key has length of 7")
    void checkThatTheKeyHasLengthOf7() {
        Assertions.assertTrue(dto.keyHasSevenDigits());
    }
}
