package com.donesvad.scenario;

import com.donesvad.action.AiraloAction;
import com.donesvad.configuration.SpringConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@CommonsLog
@SpringBootTest(classes = SpringConfiguration.class)
public abstract class BaseTest {

  @Autowired protected AiraloAction airaloAction;
  @Autowired private ObjectMapper objectMapper;

  @BeforeAll
  public static void setup(@Autowired Environment env) {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    List<Filter> filters = new LinkedList<>();
    filters.add(new AllureRestAssured());
    if (Boolean.parseBoolean(env.getProperty("log.rest-assured-responses", "false"))) {
      filters.add(new ResponseLoggingFilter());
    }
    RestAssured.filters(filters);
    RestAssured.useRelaxedHTTPSValidation();
  }

  @AfterAll
  public static void tearDown() {}

  @BeforeEach
  public void init() {
    RestAssured.config =
        RestAssuredConfig.config()
            .objectMapperConfig(
                new ObjectMapperConfig()
                    .jackson2ObjectMapperFactory((cls, charset) -> objectMapper));
  }

  @AfterEach
  public void cleanUp() {}
}
