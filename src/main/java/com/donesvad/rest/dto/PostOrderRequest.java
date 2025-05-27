package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostOrderRequest {
  @JsonProperty("package_id")
  private String packageId;

  @JsonProperty("quantity")
  private Integer quantity;
}
