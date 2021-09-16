package com.sushmita.github.service;

import com.sushmita.github.model.User;
import com.sushmita.github.model.dto.UserDTO;

public interface UserRestService {
   UserDTO findByUserName(String userName);
   UserDTO findByUserName(String userName, String password);
   UserDTO findByEmail(String email);
   void save(UserDTO user);
}
