package com.example.chatapp.dto.response;

import com.example.chatapp.config.CustomUserDetails;
import com.example.chatapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;
    private CustomUserDetails user;



}
