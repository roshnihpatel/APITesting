package com.sparta.rp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTests {

    @Test
    @DisplayName("Check status code of website")
    void checkStatusCodeOfWebsite() {
        RestAssured
                .get("https://www.spartaglobal.com")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Check the server software for the website")
    void checkTheServerSoftwareForTheWebsite() {
        given()
                .response()
                .when()
                .get("https://www.spartaglobal.com")
                .getHeader("X-Powered-By")
                .equals("ASP.NET");
    }
}

