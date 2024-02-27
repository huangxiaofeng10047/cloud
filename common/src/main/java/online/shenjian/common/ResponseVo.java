package online.shenjian.common;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hxf16
 */
@Data
@Schema(name = "ResponseVo", description = "响应对象")
public class ResponseVo<T> implements Serializable {
    
    @Schema(description = "响应码", name = "code")
    private Integer code;
    
    @Schema(description = "响应信息", name = "message")
    private String message;
    
    @Schema(description = "响应数据", name = "data")
    private T data;
    
    public static ResponseVo message(int code, String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(code);
        responseVo.setMessage(message);
        return responseVo;
    }
    
    public static ResponseVo message(ResponseCode responseCode) {
        ResponseVo responseVo = message(responseCode.val(), responseCode.des());
        return responseVo;
    }
    
    public static ResponseVo message(int code, String message, Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(code);
        responseVo.setMessage(message);
        responseVo.setData(data);
        return responseVo;
    }
    
    public static ResponseVo success() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseCode.SUCCESS.val());
        return responseVo;
    }
    
    public static ResponseVo success(Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseCode.SUCCESS.val());
        responseVo.setData(data);
        return responseVo;
    }
    
    public static ResponseVo error(String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseCode.FAIL.val());
        responseVo.setMessage(message);
        return responseVo;
    }
    
    public ResponseVo setResult(ResponseCode resultCode) {
        this.code = resultCode.val();
        this.message = resultCode.des();
        return this;
    }
    
    public ResponseVo setResult(ResponseCode resultCode, T data) {
        this.code = resultCode.val();
        this.message = resultCode.des();
        this.setData(data);
        return this;
    }
    
    public ResponseVo setResult(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.setData(data);
        return this;
    }
}

