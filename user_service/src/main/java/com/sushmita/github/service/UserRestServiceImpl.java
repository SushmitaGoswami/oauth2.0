package com.sushmita.github.service;

import com.sushmita.github.model.User;
import com.sushmita.github.model.dto.UserDTO;
import com.sushmita.github.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRestServiceImpl implements UserRestService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public UserDTO findByUserName(String userName) {
        Iterable<User> all = userRepository.findAll();
        System.out.println(all);

        User byUserName = userRepository.findByUserName(userName);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(byUserName, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO findByUserName(String userName, String password) {
        User byUserName = userRepository.findByUserName(userName);
        UserDTO userDTO = null;

        if (encoder.matches(password,
                byUserName.getEncryptedPassword())) {
            userDTO = new UserDTO();
            BeanUtils.copyProperties(byUserName, userDTO);
        }
        return userDTO;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setEncryptedPassword(encoder.encode(userDTO.getPassword()));
        System.out.println("In Save " + user);
        userRepository.save(user);
    }

}
