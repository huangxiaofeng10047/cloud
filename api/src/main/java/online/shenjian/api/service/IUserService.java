package online.shenjian.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import online.shenjian.api.entity.UserModel;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.client.common.ResponseVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huangxf
 * @since 2023-11-06
 */
public interface IUserService extends IService<UserModel> {
    public Object login(UserInfoDto userDto);
}
