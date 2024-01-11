package online.shenjian.api.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import online.shenjian.client.cloud.dto.LoginUserDto;
import online.shenjian.api.entity.UserModel;
import online.shenjian.api.mapper.UserMapper;
import online.shenjian.api.util.CommonDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultUserDetailsServiceImpl implements UserDetailsService {

    private UserMapper userMapper;

    @Autowired
    private DefaultUserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", userId);
        UserModel userInfo = userMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            log.info("登录用户账户：{} 不存在", userId);
            throw new UsernameNotFoundException("登录用户：" + userId + " 不存在");
        }
        LoginUserDto loginUserDto = CommonDtoUtils.transform(userInfo, LoginUserDto.class);
        return loginUserDto;
    }
}
