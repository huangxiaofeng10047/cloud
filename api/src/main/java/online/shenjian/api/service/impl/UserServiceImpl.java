package online.shenjian.api.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.shenjian.api.component.Utils;
import online.shenjian.api.entity.UserModel;
import online.shenjian.api.mapper.UserMapper;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.client.common.ResponseVo;
import online.shenjian.api.service.IUserService;
import org.springframework.stereotype.Service;
import online.shenjian.client.cloud.dto.Claims;
import online.shenjian.api.util.TokenUtils;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangxf
 * @since 2023-11-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements IUserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseVo login(UserInfoDto userDto) {
        // 根据用户登录名获取用户实体
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDto.getUsername());
        wrapper.last("LIMIT 1");
        UserModel user = userMapper.selectOne(wrapper);
        // 判断是否存在该用户
        if (user == null) {
            return ResponseVo.error(Utils.getI18n("user.login.error", null));
        }

        // 假设用户一定存在且密码正确

        Claims claims = new Claims();
        claims.setUserId(user.getId());
        claims.setUsername(user.getUsername());

        // 获取权限列表
        String token = TokenUtils.buildToken(claims);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        return ResponseVo.success(jsonObject);
    }
}
