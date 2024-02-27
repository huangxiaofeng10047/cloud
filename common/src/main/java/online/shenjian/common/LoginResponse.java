package online.shenjian.common;

import lombok.Data;

@Data
public class LoginResponse {
    String username;
    String token;
    String refreshToken;
}
