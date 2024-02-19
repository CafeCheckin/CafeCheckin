package com.example.CafeTour.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Pw")
    private String Pw;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickName;

    @Column(name="user_role")
    private String userRole;

    @Column(name = "user_create_dt")
    @CreationTimestamp
    private Timestamp userCreateDt;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Board> boardList;

}