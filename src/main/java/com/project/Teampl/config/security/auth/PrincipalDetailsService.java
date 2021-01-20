package com.project.Teampl.config.security.auth;

import com.project.Teampl.model.user.User;
import com.project.Teampl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 'loginProcessingUrl("/user/login")'
// /user/login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는,
// 'loadUserByUsername' 메소드가 호출된다.
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // UserDetailsService 인터페이스가 가지고 있는 메소드를 구현해야 로그인 기능을 사용할 수 있다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userid : " + username);
        User findUser = userRepository.findByUserid(username);

        if(findUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PrincipalDetails(findUser);
    }
}
