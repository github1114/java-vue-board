package com.board.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TxException extends Exception{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class Rollback extends RuntimeException {
        public Rollback() {
            super("일시적 서비스 장애입니다.");
        }
    }
}
