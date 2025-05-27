package com.donesvad.action;

import static com.donesvad.rest.util.TokenUtil.isTokenValid;
import static org.assertj.core.api.Assertions.assertThat;

import com.donesvad.rest.dto.AuthentificationRequest;
import com.donesvad.rest.dto.AuthentificationResponse;
import com.donesvad.rest.dto.GetSimListResponse;
import com.donesvad.rest.dto.GetSimResponse;
import com.donesvad.rest.dto.PostOrderRequest;
import com.donesvad.rest.dto.PostOrderResponse;
import com.donesvad.rest.service.AiraloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiraloAction {

  @Autowired protected AiraloService airaloService;

  public AuthentificationResponse authenticate(AuthentificationRequest authentificationRequest) {
    return airaloService.requestAccessToken(authentificationRequest);
  }

  public PostOrderResponse submitOrder(PostOrderRequest postOrderRequest, String accessToken) {
    assertThat(isTokenValid(accessToken)).isTrue();
    return airaloService.postOrder(postOrderRequest, accessToken);
  }

  public GetSimListResponse getSimList(String accessToken) {
    assertThat(isTokenValid(accessToken)).isTrue();
    return airaloService.getSimList(accessToken);
  }

  public void assertPostOrderResponse(
      PostOrderRequest expectedOrder, PostOrderResponse actualOrder) {
    assertThat(expectedOrder.getPackageId()).isEqualTo(actualOrder.getData().getPackageId());
    assertThat(expectedOrder.getQuantity()).isEqualTo(actualOrder.getData().getQuantity());
  }

  public void assertGetSimListResponse(
      PostOrderResponse expectedOrder, GetSimListResponse actualSimList) {
    List<GetSimResponse> expectedSims = expectedOrder.getData().getSims();
    List<GetSimResponse> actualSims = actualSimList.getData();
    assertThat(actualSims).containsAll(expectedSims);
  }
}
