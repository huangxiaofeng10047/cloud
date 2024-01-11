package online.shenjian.api.config;

import lombok.extern.slf4j.Slf4j;
import online.shenjian.client.common.ResponseVo;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hxf16
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseVo MissingRequestParameter(MissingServletRequestParameterException e) {
        return ResponseVo.error(e.getParameterName() + "不能为空");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVo exceptionHandler(Exception e) {
        log.error("系统繁忙，请稍后重试: {}", e.getMessage());
        e.printStackTrace();
        return ResponseVo.error("系统繁忙，请稍后重试");
    }
}

