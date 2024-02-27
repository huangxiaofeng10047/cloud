package online.shenjian.common;

public class ResultResponse {
    
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    
    // 只返回状态
    public static ResponseVo success() {
        return new ResponseVo().setResult(ResponseCode.SUCCESS);
    }
    
    // 成功返回数据
    public static ResponseVo success(Object data) {
        return new ResponseVo().setResult(ResponseCode.SUCCESS, data);
        
        
    }
    
    // 失败
    public static ResponseVo failure(ResponseCode resultCode) {
        return new ResponseVo().setResult(resultCode);
    }
    
    // 失败
    public static ResponseVo failure(ResponseCode resultCode, Object data) {
        return new ResponseVo().setResult(resultCode, data);
    }
    
    // 失败
    public static ResponseVo failure(Integer resultCode, String msg, Object data) {
        return new ResponseVo().setResult(resultCode, msg, data);
    }
    
}