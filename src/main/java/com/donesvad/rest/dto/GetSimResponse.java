package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GetSimResponse {
  @JsonProperty("id")
  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("iccid")
  private String iccid;

  @JsonProperty("lpa")
  private String lpa;

  @JsonProperty("imsis")
  private String imsis;

  @JsonProperty("matching_id")
  private String matchingId;

  @JsonProperty("qrcode")
  private String qrcode;

  @JsonProperty("qrcode_url")
  private String qrcodeUrl;

  @JsonProperty("airalo_code")
  private String airaloCode;

  @JsonProperty("apn_type")
  private String apnType;

  @JsonProperty("apn_value")
  private String apnValue;

  @JsonProperty("is_roaming")
  private Boolean isRoaming;

  @JsonProperty("confirmation_code")
  private String confirmationCode;

  @JsonProperty("apn")
  private ApnDataResponse apn;

  @JsonProperty("msisdn")
  private String msisdn;

  @JsonProperty("direct_apple_installation_url")
  private String directAppleInstallationUrl;
}
