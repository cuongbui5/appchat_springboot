package com.example.chatapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshException extends RuntimeException {
    public TokenRefreshException(String message) {
        super(message);
    }

}
