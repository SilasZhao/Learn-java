package com.example.demo.exception;

import com.example.demo.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionhandler {
    /**
     * 拦截业务异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandle(BusinessException e){
        log.error("捕捉到业务异常", e);
        return ResponseResult.failure(e.getCode(),e.getMessage());
    }
    /**
     * 拦截运行时异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandler(RuntimeException e){
        log.error("捕捉到运行时异常： ", e);

        return ResponseResult.failure(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
                e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult ThrowableHandler(Throwable th){
        log.error("捕捉到Throwable异常： ", th);

        return ResponseResult.failure(ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                th.getMessage());
    }
}
