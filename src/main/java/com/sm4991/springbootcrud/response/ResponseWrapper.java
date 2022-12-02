package com.sm4991.springbootcrud.response;


import com.sm4991.springbootcrud.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@Builder
public class ResponseWrapper {

    @Builder.Default
    public String timestamp = DateUtil.convertLocalDateTimeToFormat(LocalDateTime.now());
    public String status;
    public Integer code;
    public String error;
    public String message;
    public String path;
    public Object result;

    public static ResponseWrapper success(@Nullable HttpServletRequest request, String message, @Nullable Object result){
        String path = "";
        if(request != null) {
            path = request.getRequestURI();
        }
        return ResponseWrapper.builder()
                .status("success")
                .code(HttpStatus.OK.value())
                .message(message)
                .path(path)
                .result(result)
                .build();
    }

    public static ResponseWrapper error(@Nullable HttpServletRequest request, HttpStatus code, String errorMsg, @Nullable Object result){
        String path = "";
        if(request != null) {
            path = request.getRequestURI();
        }
        return ResponseWrapper.builder()
                .status("error")
                .code(code.value())
                .error(errorMsg)
                .path(path)
                .result(result)
                .build();
    }
}
