package com.atguigu.spzx.common.exception;


import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者:hfj
 * 功能:统一异常处理类
 * 日期: 2025/12/24 16:08
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(){
        return Result.build(null , ResultCodeEnum.SYSTEM_ERROR);
    }

    //自定义异常处理类
    @ExceptionHandler(value = GuiguException.class)
    @ResponseBody
    public Result error(GuiguException exception) {
        return Result.build(null , exception.getResultCodeEnum());
    }
}
