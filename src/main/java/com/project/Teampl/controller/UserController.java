package com.project.Teampl.controller;

import com.project.Teampl.dto.user.JoinForm;
import com.project.Teampl.dto.user.LoginForm;
import com.project.Teampl.model.user.User;
import com.project.Teampl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String admin(){
        return "user/userList";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user/loginForm";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "user/joinForm";
    }

    @PostMapping("/joinProc")
    public String joinProc(@Valid JoinForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "user/joinForm";
        }

        userService.save(User.createUser(form));
        return "redirect:/login";
    }

//    @GetMapping(value="/all", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users = userService.findAll();
//        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//    }

}
