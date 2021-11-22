package com.example.code.security;

import com.example.code.model.User;
import com.example.code.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            return SecurityUser.fromUser(user);
        }catch (UsernameNotFoundException usernameNotFoundException) {
            log.error(usernameNotFoundException.getMessage());
            throw new UsernameNotFoundException("User wasn't found in the database");
        }
    }
}
