package com.sushmita.github.controller;


import com.sushmita.github.model.dto.UserDTO;
import com.sushmita.github.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserRestService userRestService;

    @GetMapping("/get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO findByUserName(@PathVariable("username")String userName){
        return userRestService.findByUserName(userName);
    }

    @PostMapping("/authenticate/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO authenticate(@PathVariable("username") String userName,
                                      @RequestBody String password){
        return userRestService.findByUserName(userName, password);
    }


    @PostMapping("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(@RequestBody UserDTO userDTO){
        userRestService.save(userDTO);
    }
}
