package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * 作者:hfj
 * 功能:自定义异常处理类
 * 日期: 2025/12/24 16:12
 */
@Data
public class GuiguException extends RuntimeException{

    //异常状态码
    private Integer code;

    //异常信息
    private String message;

    //异常枚举类
    private ResultCodeEnum resultCodeEnum;

    public GuiguException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public GuiguException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
