package cn.race.teacher.exception;


import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.common.response.ErrorCode;
import cn.race.common.response.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //oggerFactory.getLogger可以在IDE控制台打印日志，便于开发，一般加在最上面
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse processExcetion(HttpServletRequest request, HttpServletResponse
            response, Exception e) {
        //如果是自定义异常则直接取出异常信息
        if (e instanceof BusinessException) {
            LOGGER.info(e.getMessage(), e);
            BusinessException businessException = (BusinessException) e;
            ErrorCode errorCode = businessException.getErrorCode();
            int code = errorCode.getCode();
            String desc = errorCode.getDesc();
            return new RestErrorResponse(String.valueOf(code), desc);
        }
        LOGGER.error("系统异常", e);
        return new RestErrorResponse(String.valueOf(CommonErrorCode.UNKNOWN.getCode()), CommonErrorCode.UNKNOWN.getDesc());
    }


}
