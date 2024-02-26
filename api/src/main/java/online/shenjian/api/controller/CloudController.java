package online.shenjian.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import online.shenjian.client.CloudClient;
import online.shenjian.client.annotation.ResponseResult;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.client.common.ResponseVo;
import online.shenjian.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hxf16
 */
@RestController
@Tag(name = "用户管理")
//@ResponseResult
public class CloudController implements CloudClient {
    @Autowired
    IUserService userService;
    @Override
    public Object login(UserInfoDto userInfoDto)  {
        return  userService.login(userInfoDto);
    }

    @Override
    public ResponseVo testToken(UserInfoDto userDto) {
        return ResponseVo.success("token验证成功");
    }
}

