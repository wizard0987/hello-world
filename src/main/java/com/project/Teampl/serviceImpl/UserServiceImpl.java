package com.project.Teampl.serviceImpl;

import com.project.Teampl.exception.ResourceNotFoundException;
import com.project.Teampl.model.user.User;
import com.project.Teampl.repository.UserRepository;
import com.project.Teampl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e->users.add(e));
        return users;
    }

//    @Override
//    public User findById(Long useridx) {
//        User user = userRepository.findById(useridx).orElseThrow(()-> new ResourceNotFoundException("User", "useridx",useridx));
//        return user;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
//        User findUser = userRepository.findByUserid(userid);
//
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return findUser.getRole();
//            }
//        });
//
//        return new org.springframework.security.core.userdetails.User(findUser.getUserpw(), findUser.getUserid(), authorities);
//    }

    @Override
    @Transactional
    public void deleteById(Long useridx) {
        userRepository.deleteById(useridx);
    }

    @Override
    @Transactional
    public User save(User user) {
//        user.setUserpw(passwordEncoder.encode(user.getUserpw()));
        user.setUserpw(encoder.encode(user.getUserpw()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void updateById(Long useridx, User user) {
        User localUser = userRepository.findById(useridx).orElseThrow(()-> new ResourceNotFoundException("User", "useridx",useridx));
        localUser.setUsername(user.getUsername());
        localUser.setUserpw((user.getUserpw()));
        userRepository.save(user);
    }
}
