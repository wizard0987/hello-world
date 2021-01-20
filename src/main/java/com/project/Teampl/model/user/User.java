package com.project.Teampl.model.user;

import com.project.Teampl.dto.user.JoinForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long idx;
    private String username;
    private String userid;
    private String userpw;
    private String email;
    private String role;
    @CreationTimestamp
    private Timestamp timestamp;

    public static User createUser(JoinForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setUserid(form.getUserid());
        user.setUserpw(form.getUserpw());
        user.setEmail(form.getEmail());

        return user;
    }

}
