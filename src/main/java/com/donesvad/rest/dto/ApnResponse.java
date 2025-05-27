package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApnResponse {
  @JsonProperty("apn_type")
  private String apnType;

  @JsonProperty("apn_value")
  private String apn_value;
}
