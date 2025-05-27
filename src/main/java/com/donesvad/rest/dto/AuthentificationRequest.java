package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthentificationRequest {
  @JsonProperty("client_id")
  private final String clientId;

  @JsonProperty("client_secret")
  private final String clientSecret;

  @JsonProperty("grant_type")
  private final String grantType;
}
