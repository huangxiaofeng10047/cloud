package online.shenjian.api.config;
import lombok.extern.slf4j.Slf4j;
import online.shenjian.common.ResponseVo;
import online.shenjian.common.ResultResponse;
import online.shenjian.client.exception.UserNotExistException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseBody
    public ResponseVo userNotExistException(UserNotExistException e) {
        //获取异常中的错误码和提示信息
        Integer code = e.getCode();
        String msg = e.getMsg();
        //返回统一的响应格式
        return ResultResponse.failure(code, msg, null) ;
    }
}

