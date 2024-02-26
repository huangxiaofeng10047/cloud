package online.shenjian.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.shenjian.api.component.Utils;
import online.shenjian.api.service.IUserService;
import online.shenjian.client.annotation.ResponseResult;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.client.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangxf
 * @since 2023-11-06
 */
@RestController
@RequestMapping("/user-model")
@ResponseResult
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "登录",parameters = {@Parameter(name = "userDto",description = "json 用户类")},tags = "用户管理",security = { @SecurityRequirement(name = "token")}  )
    public Object login(UserInfoDto userInfoDto)  {
        if(userInfoDto ==null){
            return null;    //国际化
        }
        return  userService.login(userInfoDto);
    }
    @PostMapping(value = "/testToken", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "测试Token", tags = "用户管理", security = { @SecurityRequirement(name = "token")})
    public ResponseVo testToken(UserInfoDto userDto) {
        return ResponseVo.success("token验证成功");
    }
}
