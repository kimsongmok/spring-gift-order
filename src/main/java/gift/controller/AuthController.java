package gift.controller;

import gift.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  private final KakaoService kakaoService;

  @Autowired
  public AuthController(KakaoService kakaoService) {
    this.kakaoService = kakaoService;
  }

  @GetMapping("/login")
  public String login() {
    String authorizationUrl = kakaoService.getAuthorizationUrl();
    return "redirect:" + authorizationUrl;
  }

  @GetMapping("/oauth/callback/kakao")
  public String callback(@RequestParam String code, Model model) {
    String token = kakaoService.getToken(code);
    model.addAttribute("token", token);
    return "success";
  }
}
