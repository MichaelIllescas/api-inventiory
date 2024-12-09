
package com.inventiory.Api.auth;

import com.inventiory.Api.Entity.Role;
import com.inventiory.Api.Entity.User;
import com.inventiory.Api.config.JwtService;
import com.inventiory.Api.repository.UserRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonii
 */
@Service
@RequiredArgsConstructor        
class AuthenticationService {
    
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final JwtService jwtService; 
          
    private final AuthenticationManager authenticationManager;
            
  public final AuthenticationResponse register(RegisterRequest request) {

    // Determinar el rol basado en el campo enviado desde el frontend
    var role = request.getRole() != null ? request.getRole() : Role.USER; // Por defecto es USER si no se especifica

    var user = User.builder()
                   .firstName(request.getFirstname())
                   .lastName(request.getLastname())
                   .email(request.getEmail())
                   .password(passwordEncoder.encode(request.getPassword()))
                   .role(role) // Asignar el rol del usuario
                   .fechaRegistro(new Date())
                   .build();
    
    userRepository.save(user);
    
    var jwt = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
                                 .token(jwt)
                                 .build();
}

    
        public AuthenticationResponse login (AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                        
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
        var jwt = jwtService.generateToken(user);
        
         return AuthenticationResponse.builder()
                                    .token(jwt)
                                    .build();
        
}
    
}
