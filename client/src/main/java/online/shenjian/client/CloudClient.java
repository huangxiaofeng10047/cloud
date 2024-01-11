package online.shenjian.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.client.common.ResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hxf16
 */
@FeignClient(value = "cloud", contextId = "cloud")
@Component
public interface CloudClient {

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "登录",parameters = {@Parameter (name = "userDto",description = "json 用户类")},tags = "用户管理",security = { @SecurityRequirement(name = "token")}  )
    ResponseVo login(@RequestBody @Parameter(description = "用户实体")  UserInfoDto userInfoDto);

    @PostMapping(value = "/testToken", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "测试Token", tags = "用户管理", security = { @SecurityRequirement(name = "token")})
    ResponseVo testToken(@RequestBody UserInfoDto userDto);
}
