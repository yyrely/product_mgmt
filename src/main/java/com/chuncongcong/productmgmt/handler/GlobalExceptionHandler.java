package com.chuncongcong.productmgmt.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.chuncongcong.productmgmt.exception.BaseErrorCode;
import com.chuncongcong.productmgmt.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Hu
 * @date 2018/12/23 16:27
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultExceptionHandler(HttpServletResponse response, Exception e) {
        log.error("exception info:", e);
        if (e instanceof ServiceException) {
            response.setStatus(((ServiceException)e).getHttpCode());
            return new ResultBean<>(e);
        } else {
            ServiceException exception = new ServiceException(BaseErrorCode.SYSTEM_ERROR,
                !StringUtils.isEmpty(e.getMessage()) ? e.getMessage() : BaseErrorCode.SYSTEM_ERROR.getMessage());
            response.setStatus(exception.getHttpCode());
            return new ResultBean<>(exception);
        }
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object notFoundExceptionHandler() {
        return new ResultBean<>(new ServiceException(BaseErrorCode.API_NOT_FOUND));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object argumentNotValidExceptionHandler(Exception e) {
        log.error("argument exception:", e);
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)e;
        List<String> defaultMsg = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
            .map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return new ResultBean<>(new ServiceException(defaultMsg.get(0)));
    }
}
