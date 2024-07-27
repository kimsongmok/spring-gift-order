package gift.controller;

import gift.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/login")
  public String login() {
    String authorizationUrl = authService.getAuthorizationUrl();
    return "redirect:" + authorizationUrl;
  }

  @GetMapping("/oauth/callback/kakao")
  public String callback(@RequestParam String code, Model model) {
    String token = authService.getToken(code);
    model.addAttribute("token", token);
    return "success";
  }
}