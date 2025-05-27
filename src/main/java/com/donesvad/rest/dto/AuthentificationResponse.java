package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthentificationResponse {
  @JsonProperty("data")
  private AuthentificationDataResponse data;

  @JsonProperty("meta")
  private MetaResponse meta;
}
