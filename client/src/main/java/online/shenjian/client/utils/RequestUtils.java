package online.shenjian.client.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtils {

    /**
     * 获取上下文request
     * @return
     */
    public static HttpServletRequest getRequest() {
  return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
 }
 public static HttpServletResponse getResponse() {
  return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
 }

}
