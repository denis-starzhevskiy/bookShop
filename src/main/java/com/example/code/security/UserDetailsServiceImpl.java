//package com.example.code.security;
//
//import com.example.code.model.User;
//import com.example.code.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user;
//        try {
//            user = userRepository.findByEmail(email);
//        }catch (UsernameNotFoundException usernameNotFoundException){
//            throw usernameNotFoundException;
//        }
//        return SecurityUser.fromUser(user);
//    }
//}
