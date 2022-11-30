package com.java.crudbolierplate.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

public class ResponseEntityWrapper2 extends ResponseEntity {

    public ResponseEntityWrapper2(HttpStatus status) {
        super(status);
    }

    public ResponseEntityWrapper2(Object body, HttpStatus status) {
        super(body, status);
    }

    public ResponseEntityWrapper2(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public ResponseEntityWrapper2(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public ResponseEntityWrapper2(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public static ResponseEntity ok(String message, @Nullable Object result) {
        return ResponseEntity.ok().body(successBody(HttpStatus.OK, message, result));
    }

    public static ResponseEntity badRequest(String message, @Nullable Object result) {
        return ResponseEntity.badRequest().body(errorBody(HttpStatus.BAD_REQUEST, message, result));
    }

    public static ResponseEntity notFound(String message) {
        return new ResponseEntity<>(errorBody(HttpStatus.NOT_FOUND, message, null), ResponseEntity.EMPTY.getHeaders(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity internalServerError(String message) {
        return ResponseEntity.internalServerError().body(errorBody(HttpStatus.INTERNAL_SERVER_ERROR, message, null));
    }

    private static ResponseWrapper2 successBody(HttpStatus status, String message, @Nullable Object result) {
        return ResponseWrapper2.builder()
                .status(status.value())
                .message(message)
                .result(result)
                .build();
    }

    private static ResponseWrapper2 errorBody(HttpStatus status, String errorMsg, @Nullable Object result) {
        return ResponseWrapper2.builder()
                .status(status.value())
                .error(errorMsg)
                .result(result)
                .build();
    }
}
