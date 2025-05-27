package com.donesvad.scenario;

import com.donesvad.rest.dto.AuthentificationRequest;
import com.donesvad.rest.dto.AuthentificationResponse;
import com.donesvad.rest.dto.GetSimListResponse;
import com.donesvad.rest.dto.PostOrderRequest;
import com.donesvad.rest.dto.PostOrderResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@CommonsLog
public class AiraloTest extends BaseTest {

  @Value("${user.client_id}")
  private String clientId;

  @Value("${user.client_secret}")
  private String clientSecret;

  @Value("${user.grant_type}")
  private String grantType;

  @Value("${order.package_id}")
  private String packageId;

  @Value("${order.quantity}")
  private Integer quantity;

  @Test
  void postOrderTest() {
    AuthentificationRequest authentificationRequest =
        AuthentificationRequest.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .grantType(grantType)
            .build();
    AuthentificationResponse authenticate = airaloAction.authenticate(authentificationRequest);

    PostOrderRequest postOrderRequest =
        PostOrderRequest.builder().packageId(packageId).quantity(quantity).build();
    String accessToken = authenticate.getData().getAccessToken();
    PostOrderResponse postOrderResponse = airaloAction.submitOrder(postOrderRequest, accessToken);
    airaloAction.assertPostOrderResponse(postOrderRequest, postOrderResponse);

    GetSimListResponse simList = airaloAction.getSimList(accessToken);
    airaloAction.assertGetSimListResponse(postOrderResponse, simList);
  }
}
