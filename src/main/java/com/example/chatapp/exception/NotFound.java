package com.example.chatapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFound extends RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
