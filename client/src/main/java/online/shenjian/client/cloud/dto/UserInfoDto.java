package online.shenjian.client.cloud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hxf16
 */
@Data
public class UserInfoDto {
    @Schema(description = "用户id")
    private String userId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "账户")
    private String account;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "组织id")
    private String orgCode;
    @Schema(description = "状态")
    private String orgName;

    @Schema(description = "角色id")
    private String roleId;
    @Schema(description = "角色名")
    private String roleName;
}
