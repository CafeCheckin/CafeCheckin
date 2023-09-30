package com.example.CafeTour.user;

import com.example.CafeTour.board.Board;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "user")
    private List<Board> boardList;

    @Builder
    public User(Long id, String pw, String email, String nickName, String userRole, Timestamp userCreateDt, List<Board> boardList) {
        this.id = id;
        this.Pw = pw;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
        this.userCreateDt = userCreateDt;
        this.boardList = boardList;
    }

}