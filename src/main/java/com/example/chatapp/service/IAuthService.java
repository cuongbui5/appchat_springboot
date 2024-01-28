package com.example.chatapp.service;


import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.RegisterRequest;
import com.example.chatapp.dto.response.BaseResponse;
import com.example.chatapp.dto.response.LoginResponse;

public interface IAuthService {
    BaseResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);


}
