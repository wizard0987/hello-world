package com.project.Teampl.dto.user;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class JoinForm {
    @NotEmpty(message = "이름을 입력해주셔야 합니다.")
    private String username;
    @NotEmpty(message = "아이디를 입력해주셔야 합니다.")
    private String userid;
    @NotEmpty(message = "패스워드를 입력해주셔야 합니다.")
    private String userpw;
    private String email;
    private String auth;
}
