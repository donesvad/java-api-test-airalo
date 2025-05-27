package com.donesvad.rest.service;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import com.donesvad.rest.dto.AuthentificationRequest;
import com.donesvad.rest.dto.AuthentificationResponse;
import com.donesvad.rest.dto.GetSimListResponse;
import com.donesvad.rest.dto.PostOrderRequest;
import com.donesvad.rest.dto.PostOrderResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ParamConfig;
import io.restassured.config.ParamConfig.UpdateStrategy;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiraloService {

  public static final String tokenPath = "/token";
  public static final String orderPath = "/orders";
  public static final String simPath = "/sims";

  private RequestSpecification requestSpec;

  @Value("${airalo-service-rest.host}")
  private String host;

  @Value("${airalo-service-rest.port:#{null}}")
  private Integer port;

  @Value("${airalo-service-rest.protocol}")
  private String protocol;

  @Value("${airalo-service-rest.base-path}")
  private String basePath;

  @PostConstruct
  public void init() {
    String baseUri = protocol + "://" + host;
    if (port != null) {
      baseUri += ":" + port;
    }
    requestSpec =
        new RequestSpecBuilder()
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .log(LogDetail.BODY)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setBaseUri(baseUri)
            .setBasePath(basePath)
            .setConfig(
                config()
                    .paramConfig(
                        ParamConfig.paramConfig()
                            .queryParamsUpdateStrategy(UpdateStrategy.REPLACE)))
            .build();
  }

  private Response requestAccessTokenResponse(AuthentificationRequest authentificationRequest) {
    return given(requestSpec).body(authentificationRequest).post(tokenPath);
  }

  public AuthentificationResponse requestAccessToken(
      AuthentificationRequest authentificationRequest) {
    return requestAccessTokenResponse(authentificationRequest)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(AuthentificationResponse.class);
  }

  private Response postOrderResponse(PostOrderRequest postOrderRequest, String accessToken) {
    return given(requestSpec).auth().oauth2(accessToken).body(postOrderRequest).post(orderPath);
  }

  public PostOrderResponse postOrder(PostOrderRequest postOrderRequest, String accessToken) {
    return postOrderResponse(postOrderRequest, accessToken)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(PostOrderResponse.class);
  }

  private Response getSimListResponse(String accessToken) {
    return given(requestSpec).auth().oauth2(accessToken).get(simPath);
  }

  public GetSimListResponse getSimList(String accessToken) {
    return getSimListResponse(accessToken)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(GetSimListResponse.class);
  }
}
