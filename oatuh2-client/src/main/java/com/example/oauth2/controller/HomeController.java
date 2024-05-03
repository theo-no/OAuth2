package com.example.oauth2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    final OAuth2AuthorizedClientService clientService;

    @GetMapping("/home")
    public String index(Authentication authentication, Model model) {
        System.out.println("Authentication : " + authentication);
        DefaultOidcUser oAuth2User = (DefaultOidcUser) authentication.getPrincipal();
        String token = oAuth2User.getIdToken().getTokenValue();

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        String accessToken = client.getAccessToken().getTokenValue();

        model.addAttribute("idToken", token);
        model.addAttribute("acToken", accessToken);
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:http://localhost:8000/home";
    }

}
