package com.sparta.rp;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.rp.pojo.Rates;
import com.sparta.rp.pojo.RatesPojo;
import org.junit.jupiter.api.*;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;


public class RatesPojoTests {
    private static RatesPojo rates;

    @BeforeAll
    static void initAll() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            rates = mapper.readValue(new FileReader("src/test/resources/rates.json"), RatesPojo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " is executing");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " is finished");
    }

    @Nested
    @Tag("successCheck")
    class SuccessTests {

        @Test
        @DisplayName("check that success is not null")
        void checkThatSuccessIsNotNull() {
            Assertions.assertNotNull(rates.isSuccess());
        }

        @Test
        @DisplayName("check that success is a boolean")
        void checkThatSuccessIsABoolean() {
            Assertions.assertTrue(rates.isSuccess() instanceof Boolean);
        }

        @Test
        @DisplayName("Check if success is true")
        void checkIfSuccessIsBoolean() {
            Assertions.assertTrue(rates.isSuccess());
        }

    }
    @Nested
    @Tag("timestampCheck")
    class TimestampTest{
        LocalDate epochAsDate;
        @BeforeEach
        void init(){
            epochAsDate = Instant.ofEpochSecond(rates.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate();

        }

        @Test
        @DisplayName("check that timestamp is a valid date in the past")
        void checkThatTimestampIsAValidEpoch() {

            Assertions.assertTrue(epochAsDate.isBefore(LocalDate.now()));
        }

        @Test
        @DisplayName("check that timestamp is the same a date")
        void checkThatTimestampIsTheSameADate() {
            LocalDate dateAsLocalDate = LocalDate.parse( rates.getDate());
            System.out.println(dateAsLocalDate);
            Assertions.assertEquals(dateAsLocalDate,epochAsDate);
        }

    }

    @Nested
    @Tag("baseCheck")
    class BaseTests{
        @Test
        @DisplayName("Check that the base is the a 3 letter uppercase string")
        void checkThatTheBaseIsTheA3LetterUppercaseString() {
            Assertions.assertTrue(rates.getBase().matches("[A-Z]{3}"));
        }

        @Test
        @DisplayName("Check that the base is a field in rates")
        void checkThatTheBaseIsAFieldInRates() {
            StringBuilder baseAsDeclaredField = new StringBuilder();
            baseAsDeclaredField.append("private java.lang.Double com.sparta.rp.pojo.Rates.");
            baseAsDeclaredField.append(rates.getBase().toLowerCase().charAt(0));
            baseAsDeclaredField.append(rates.getBase().substring(1));

            Field[] ratesDeclaredFields = (rates.getRates().getClass().getDeclaredFields());
            boolean match = Arrays.stream(ratesDeclaredFields)
                    .filter(field -> field.toString().equals(baseAsDeclaredField.toString()))
                    .count() == 1;

            Assertions.assertTrue(match);
        }

        @Test
        @DisplayName("check that the base in rates is 1")
        void checkThatTheBaseInRatesIs1() {
            String getBase = "get".concat(rates.getBase());
            Method[] ratesDeclaredMethods = rates.getRates().getClass().getDeclaredMethods();
            try {
                Method method = Rates.class.getDeclaredMethod(getBase);
                Object obj = method.invoke(rates.getRates());
                Assertions.assertEquals(1.0, parseDouble(obj.toString()));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    @Tag("ratesCheck")
    class RatesTests{

        @Test
        @DisplayName("check that there are 168 currencies in rates")
        void checkThatThereAre168CurrenciesInRates() {

            Field[] ratesDeclaredFields = (rates.getRates().getClass().getDeclaredFields());
            long numberOfCurrencies = Arrays.stream(ratesDeclaredFields).
                    count();
            Assertions.assertEquals(168, numberOfCurrencies);
        }

        @Test
        @DisplayName("check that all the currencies in rates are upperCase and 3 letters")
        void checkThatAllTheCurrenciesInRatesAreUpperCaseAnd3Letters() {
            Field[] ratesDeclaredFields = rates.getRates().getClass().getDeclaredFields();
            long count = Arrays.stream(ratesDeclaredFields)
                    .filter(field -> field.getAnnotation(JsonProperty.class).value().matches("[A-Z]{3}"))
                    .count();
            Assertions.assertEquals(168, count);

        }
    }
}
