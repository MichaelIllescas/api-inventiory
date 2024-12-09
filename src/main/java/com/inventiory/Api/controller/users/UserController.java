
package com.inventiory.Api.controller.users;

import com.inventiory.Api.Entity.User;
import com.inventiory.Api.Service.UserService;
import com.inventiory.Api.dto.UsuarioDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jonii
 */
@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/verUsuarios")
    public ResponseEntity<List<UsuarioDTO>> cargarUsuarios()
    {
          return ResponseEntity.ok(userService.obtenerUsuarios().stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getFirstName(),
                        usuario.getLastName(),
                        usuario.getEmail(),
                        usuario.getRole().toString(),
                        usuario.getFechaRegistro(),
                        usuario.isEnabled()
                ))
                .collect(Collectors.toList())
    
          );
    }
    

    
}
