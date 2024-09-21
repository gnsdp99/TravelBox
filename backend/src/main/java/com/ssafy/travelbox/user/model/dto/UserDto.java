package com.ssafy.travelbox.user.model.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {
    @Schema(description = "아이디")
    private String id;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "핸드폰 번호")
    private String phoneNumber;
    @Schema(description = "계정권한")
    private String role = "USER";
    @Schema(description = "가입날짜")
    private Timestamp joinDate;
    @Schema(description = "refreshToken")
    private String refreshToken;
}

