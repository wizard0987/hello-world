package com.project.Teampl.config.security.auth;

import com.project.Teampl.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 /user/login 주소 요청이 오면 가로채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티가 소유한 별도의 session을 만들어준다.
// (Security ContextHolder라는 key 값에 sesstion을 저장하게 된다.)

// 시큐리티가 가지고 있는 그 session에 들어갈 수 있는 Object 타입이 바로 'Authentication' 객체이며,
// (Object 타입 -> Authentication 타입 객체)
// 또 그 Authentication 안에 User 정보가 있어야 하는데, 이 User 오브젝트 타입은 'UserDetails' 객체이다.
// (Authentication 타입 -> UserDetails 타입 객체)

// 즉, Security Session -> Authentication -> UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한(User.getRole())을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("유저 인덱스 : " + user.getIdx());
        System.out.println("유저 이름 : " + user.getUsername());
        System.out.println("유저 아이디 : " + user.getUserid());
        System.out.println("유저 패스워드 : " + user.getUserpw());
        System.out.println("유저 이메일 : " + user.getEmail());
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getUserpw();
    }

    @Override
    public String getUsername() {
        return user.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 우리 사이트에서 1년동안 회원이 로그인을 안하면, 휴면계정으로 하기로 했다면,
        // User 클래스 내에 'private TimeStamp loginDate'와 같은 필드값을 이용해 휴먼 계정을
        // 설정할 수 있다.
        // 현재시간 - 로그인 시간 -> 1년을 초과하면 false로 반환
        return true;
    }
}
