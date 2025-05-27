package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class GetSimListResponse {
  @JsonProperty("data")
  private List<GetSimResponse> data;

  @JsonProperty("meta")
  private MetaResponse meta;
}
