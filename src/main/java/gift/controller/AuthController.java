package gift.controller;

import gift.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/login")
  public String login() {
    String authorizationUrl = authService.getAuthorizationUrl();
    return authorizationUrl;
  }

  @GetMapping("/oauth/callback/kakao")
  public String callback(@RequestParam String code) {
    String token = authService.getToken(code);
    return token;
  }
}