package gift.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class KakaoService {

  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.client-secret}")
  private String clientSecret;

  @Value("${kakao.redirect-uri}")
  private String redirectUri;

  @Value("${kakao.token-url}")
  private String tokenUrl;

  private final RestTemplate restTemplate;

  public KakaoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getAuthorizationUrl() {
    return UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", redirectUri)
            .build()
            .toUriString();
  }

  public String getToken(String code) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    String body = UriComponentsBuilder.newInstance()
            .queryParam("grant_type", "authorization_code")
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", redirectUri)
            .queryParam("code", code)
            .queryParam("client_secret", clientSecret)
            .build()
            .toUriString()
            .substring(1);

    HttpEntity<String> request = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

    return response.getBody();
  }
}
