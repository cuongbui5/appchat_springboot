package com.example.chatapp.service.impl;



import com.example.chatapp.config.CustomUserDetails;
import com.example.chatapp.config.CustomUserDetailsService;
import com.example.chatapp.config.jwt.JwtService;
import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.RegisterRequest;
import com.example.chatapp.dto.response.BaseResponse;
import com.example.chatapp.dto.response.LoginResponse;
import com.example.chatapp.exception.AlreadyExistException;
import com.example.chatapp.exception.NotFound;
import com.example.chatapp.exception.WrongPassword;
import com.example.chatapp.model.Role;
import com.example.chatapp.model.User;
import com.example.chatapp.repository.RoleRepository;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;



    @Override
    public BaseResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsUserByEmail(registerRequest.getEmail())){
            throw new AlreadyExistException("Email has been registered!");
        }
        if(userRepository.existsUserByUsername(registerRequest.getUsername())){
            throw new AlreadyExistException("Username has been registered!");
        }

        Optional<Role> role= roleRepository.findRoleByName("USER");
        if(role.isEmpty()){
            throw new NotFound("Not found role : USER");
        }

        Set<Role> roles=new HashSet<>();
        roles.add(role.get());
        User user= User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
        return new BaseResponse("ok","register successful!");
    }




    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {

        try {

            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetails= userDetailsService.loadUserByUsername(loginRequest.getUsername());
            return LoginResponse.builder()
                    .accessToken(jwtService.generateToken(customUserDetails))
                    .user(customUserDetails)
                    .build();

        }catch (Exception e){
            if(e instanceof BadCredentialsException){
                throw new WrongPassword("Wrong password!");
            }

            throw new RuntimeException(e.getMessage());
        }


    }





}
