package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApnDataResponse {
  @JsonProperty("ios")
  private ApnResponse ios;

  @JsonProperty("android")
  private ApnResponse android;
}
