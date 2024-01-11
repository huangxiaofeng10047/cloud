package online.shenjian.client.cloud.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Claims {
    @Schema(description = "过期时间")
   private Long exp;
    @Schema(description = "用户id")
   private String userId;
    @Schema(description = "用户名")
   private String username;
    @Schema(description = "账户")
   private String account;
    @Schema(description = "组织id")
   private String orgCode;
    @Schema(description = "状态")
   private String state;
   private List<String> roles;
   private List<String> permissions;

    public Claims() {
    }

    public Claims(Long exp, String username, String account, String state, List<String> roles,
        List<String> permissions) {
        this.exp = exp;
        this.username = username;
        this.account = account;
        this.state = state;
        this.roles = roles;
        this.permissions = permissions;
    }
}
