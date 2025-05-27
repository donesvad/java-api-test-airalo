package com.donesvad.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class PostOrderDataResponse {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("code")
  private String code;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("package_id")
  private String packageId;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("type")
  private String type;

  @JsonProperty("description")
  private String description;

  @JsonProperty("esim_type")
  private String esimType;

  @JsonProperty("validity")
  private Integer validity;

  @JsonProperty("package")
  private String packageName;

  @JsonProperty("data")
  private String data;

  @JsonProperty("price")
  private Integer price;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("manual_installation")
  private String manualInstallation;

  @JsonProperty("qrcode_installation")
  private String qrcodeInstallation;

  @JsonProperty("installation_guides")
  private Map<String, String> installationGuides;

  @JsonProperty("text")
  private String text;

  @JsonProperty("voice")
  private String voice;

  @JsonProperty("net_price")
  private Double netPrice;

  @JsonProperty("brand_settings_name")
  private String brandSettingsName;

  @JsonProperty("sims")
  private List<GetSimResponse> sims;
}
