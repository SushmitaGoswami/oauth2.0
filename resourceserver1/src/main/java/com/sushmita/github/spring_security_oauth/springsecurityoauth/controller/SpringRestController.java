package com.sushmita.github.spring_security_oauth.springsecurityoauth.controller;


import com.sushmita.github.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/resourceserver")
@RestController
public class SpringRestController {

    @Autowired
    Environment environment;

    @GetMapping("/token")
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt){
        System.out.println(jwt);
        return jwt;
    }

    @GetMapping("/status")
    public String getStatus(){
        return "working on port= " + environment.getProperty("local.server.port");
    }


    @PostAuthorize("hasRole('openid-role') or returnObject.userId == #jwt.subject")
    @GetMapping("/user-info")
    public UserDetails getUserDetails(@AuthenticationPrincipal Jwt jwt){
        return new UserDetails("bd3a4177-1e98-4a0c-9b27-8e5185f15646","a","b");
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == #jwt.subject")
    @DeleteMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable String userId, @AuthenticationPrincipal Jwt jwt){
        System.out.println(userId);
        return "deleted user: " + userId;
    }

    @GetMapping("/user-list")
    public List<UserDetails> getUserDetails(){
        System.out.println("here...");
        return Arrays.asList(new UserDetails("1","a","b"),
                             new UserDetails("2","c","d"));
    }
}
