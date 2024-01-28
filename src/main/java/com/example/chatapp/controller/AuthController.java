package com.example.chatapp.controller;


import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.RegisterRequest;
import com.example.chatapp.dto.response.BaseResponse;
import com.example.chatapp.service.impl.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(authService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok()
                .body(authService.login(loginRequest));
    }





}
