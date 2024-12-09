/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventiory.Api.Service;

import com.inventiory.Api.Entity.User;
import com.inventiory.Api.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonii
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> obtenerUsuarios(){
    
       return userRepository.findAll();
    }
}
