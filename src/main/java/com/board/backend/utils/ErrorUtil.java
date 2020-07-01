package com.board.backend.utils;

import com.board.backend.code.ResultCode;
import org.springframework.stereotype.Component;

@Component
public class ErrorUtil {

    public ResultCode addErrorMessage(String msg){
        ResultCode resultCode = ResultCode.FAIL;
        resultCode.setMessage(msg);
        return resultCode;
    }
}
