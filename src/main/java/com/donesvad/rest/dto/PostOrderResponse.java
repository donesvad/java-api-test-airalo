package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostOrderResponse {
  @JsonProperty("data")
  private PostOrderDataResponse data;

  @JsonProperty("meta")
  private MetaResponse meta;
}
