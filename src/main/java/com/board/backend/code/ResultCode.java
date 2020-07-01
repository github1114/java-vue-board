package com.board.backend.code;

import lombok.Setter;
import org.springframework.http.HttpStatus;

public enum ResultCode {

    SUCCESS(200, ""),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(),""),
    FAIL(400,"");

    private int code;

    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
