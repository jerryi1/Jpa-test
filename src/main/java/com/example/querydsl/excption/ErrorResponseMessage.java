package com.example.querydsl.excption;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by hanxiao on 2017/6/21.
 */
@Data
public class ErrorResponseMessage {

    LocalDateTime timestamp;

    String status;

    String error;

    String message;

    String path;

    String errorCode;

    public ErrorResponseMessage(LocalDateTime timestamp, HttpStatus status, String error, String errorCode, String message, String path) {
        this.timestamp = timestamp;
        this.status = String.valueOf(status.value());
        this.error = error;
        this.message = message;
        this.path = path;
        this.errorCode = errorCode;
    }

    public ErrorResponseMessage(HttpStatus status, String error, String errorCode, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = String.valueOf(status.value());
        this.error = error;
        this.message = message;
        this.path = path;
        this.errorCode = errorCode;
    }

    public ErrorResponseMessage(HttpStatus status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = String.valueOf(status.value());
        this.error = error;
        this.message = message;
        this.path = path;
        this.errorCode = null;
    }
}
