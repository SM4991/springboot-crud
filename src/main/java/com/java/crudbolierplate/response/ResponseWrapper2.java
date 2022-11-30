package com.java.crudbolierplate.response;


import com.java.crudbolierplate.util.DateUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseWrapper2 {
    @Builder.Default
    public String timestamp = DateUtil.convertLocalDateTimeToFormat(LocalDateTime.now());
    public Integer status;
    public String error;
    public String message;
    public String path;
    public Object result;
}
