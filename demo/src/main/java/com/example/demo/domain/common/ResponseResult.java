package com.example.demo.domain.common;

import com.example.demo.exception.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -1937420523228822782L;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 编码
     */
    private String code;
    /**
     * 结果
     */
    private String message;
    private T result;
    public static<T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        return responseResult;
    }

    /**
     * 失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> failure(String code, String message){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }
    public static<T> ResponseResult<T> failure(ErrorCodeEnum codeEnum){
        return failure(codeEnum.getCode(),codeEnum.getMessage());
    }

}
