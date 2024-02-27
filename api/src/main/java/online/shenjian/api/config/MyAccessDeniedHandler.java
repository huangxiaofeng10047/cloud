package online.shenjian.api.config;

import java.io.IOException;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.shenjian.common.ResponseCode;
import online.shenjian.common.ResponseVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws
        IOException {
        response.getWriter().write(JSON.toJSONString(ResponseVo.message(ResponseCode.LICENSE_EXPIRED)));
    }
}
