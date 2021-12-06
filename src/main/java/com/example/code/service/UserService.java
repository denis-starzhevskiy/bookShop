package com.example.code.service;

import com.example.code.dto.UserDto;
import com.example.code.model.Role;
import com.example.code.model.Status;
import com.example.code.model.User;
import com.example.code.model.request.UserRequest;
import com.example.code.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Object> getAllUsers(){
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> createUser(UserRequest userRequest) {
        User user = createUserFromRequest(userRequest);
        try{
            userRepository.save(user);
            emailService.sendEmail(userRequest.getEmail(), "Дякуєм за реєстрацію у нащому сервісі!!!\nНумо купувати й читати !!!");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private User createUserFromRequest(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRegisterDate(Timestamp.from(Instant.now()));
        user.setLastLoginDate(Timestamp.from(Instant.now()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public ResponseEntity<Object> getUserInformation(String username) {
        try{
            User user = userRepository.findByUsername(username);
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setName(user.getUsername());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
