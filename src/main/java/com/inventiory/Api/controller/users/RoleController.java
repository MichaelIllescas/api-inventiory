/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventiory.Api.controller.users;

import com.inventiory.Api.Entity.Role;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
 
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/obtenerRoles")
    public ResponseEntity<List<String>> obtenerRoles() {
        // Devuelve los valores de la enumeración como una lista de strings
        List<String> roles = Arrays.stream(Role.values())
                                   .map(Role::name)
                                   .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }
}
