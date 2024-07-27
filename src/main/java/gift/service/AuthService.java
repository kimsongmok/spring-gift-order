package gift.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthService {

  @Value("${kakao.auth.url}")
  private String authUrl;

  @Value("${kakao.token.url}")
  private String tokenUrl;

  @Value("${kakao.client.id}")
  private String clientId;

  @Value("${kakao.client.secret}")
  private String clientSecret;

  @Value("${kakao.redirect.uri}")
  private String redirectUri;

  public String getAuthorizationUrl() {
    return authUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code";
  }

  public String getToken(String code) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");

    String body = "grant_type=authorization_code&client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code;
    HttpEntity<String> entity = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root.path("access_token").asText();
      } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to parse token");
      }
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to retrieve token");
    }
  }
}