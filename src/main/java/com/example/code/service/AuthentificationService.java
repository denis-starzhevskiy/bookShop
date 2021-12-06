package com.example.code.service;

import com.example.code.dto.AuthenticationRequestDTO;
import com.example.code.model.User;
import com.example.code.repository.UserRepository;
import com.example.code.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthentificationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> authenticate(AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByUsername(request.getUsername());
            String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);
            response.put("role", user.getRole().name());
            user.setLastLoginDate(Timestamp.from(Instant.now()));
            userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

//            HttpCookie httpCookie = ResponseCookie.from("token", token)
//                    .path("/")
//                    .httpOnly(true)
//                    .maxAge(604800)
//                    .sameSite("None")
//                    .secure(true)
//                    .build();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
//                    .body("username : " +  request.getUsername());
