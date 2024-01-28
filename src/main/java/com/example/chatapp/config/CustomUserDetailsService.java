package com.example.chatapp.config;



import com.example.chatapp.exception.WrongUsernameOrEmail;
import com.example.chatapp.model.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional;
        if(Helper.isValidEmail(username)){
            userOptional=userRepository.findUserByEmail(username);
        }else {
            userOptional=userRepository.findUserByUsername(username);
        }

        if(userOptional.isEmpty()){
            throw new WrongUsernameOrEmail("Invalid username!Please enter username or email again!");
        }
        User user=userOptional.get();
        return CustomUserDetails
                .builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .image(user.getImage())
                .authorities(getAuthoritiesFromUser(user))
                .build();
    }

    public List<SimpleGrantedAuthority> getAuthoritiesFromUser(User user){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
}
