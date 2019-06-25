package com.example.querydsl.base;

import com.example.querydsl.config.jackson.ant.AntPathFilterMappingJacksonValue;
import com.example.querydsl.excption.ErrorResponseMessage;
import com.example.querydsl.excption.ResponseErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiaoew on 2017/4/14.
 */
public abstract class BaseController {

    public ResponseEntity<?> ok() {
        return ResponseEntity.ok().build();
    }

    public <T> ResponseEntity<?> ok(T t) {
        return ResponseEntity.ok(pickProperties(t, "**"));
    }

    public <T> ResponseEntity<?> ok(T t, String filters) {
        return ResponseEntity.ok(pickProperties(t, filters));
    }

    public ResponseEntity<MappingJacksonValue> ok(MappingJacksonValue t) {
        return ResponseEntity.ok(t);
    }

    public ResponseEntity<?> created() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public <T> ResponseEntity<?> created(T t) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pickProperties(t, "**"));
    }

    public ResponseEntity<?> deleted() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<?> badRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<?> badRequest(ResponseErrorCode errorCode) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCode);
    }

    public ResponseEntity<?> forbidden(ResponseErrorCode error, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AntPathFilterMappingJacksonValue(new ErrorResponseMessage(HttpStatus.FORBIDDEN,
                "Forbidden", error.getDescription(), request.getServletPath())));
    }

    public <T> MappingJacksonValue pickProperties(T t, String filters) {
        if (filters == null) {
            return new AntPathFilterMappingJacksonValue(t, "**");
        } else {
            return new AntPathFilterMappingJacksonValue(t, filters.split(","));
        }
    }

}
