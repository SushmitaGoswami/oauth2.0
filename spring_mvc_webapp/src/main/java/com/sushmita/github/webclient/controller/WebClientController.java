package com.sushmita.github.webclient.controller;

import com.sushmita.github.webclient.model.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class WebClientController {

    @Autowired
    WebClient webclient;

    @Value("${apiGateway.url}")
    private String apiGateWayURL;

    @GetMapping("/users")
    public String getUsers(Model model, @AuthenticationPrincipal OidcUser oidcUser){
        String resourceURL = "/resourceserver/user-list";
        System.out.println(oidcUser);
        List<UserDetailsDTO> userDetailsDTOS = webclient.get()
                .uri(apiGateWayURL + resourceURL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDetailsDTO>>() {
                }).block();

        model.addAttribute("users", userDetailsDTOS);
        return "users";
    }

}
