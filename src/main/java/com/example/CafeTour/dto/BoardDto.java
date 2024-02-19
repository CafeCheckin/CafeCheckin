package com.example.CafeTour.dto;

import com.example.CafeTour.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
@Builder
public class BoardDto {
    private Long id;
    private String userNickName;
    private int clicks;
    private String title;
    private String boardOpinion;
    private Timestamp createDAte;
    private Timestamp modifyDate;
    private User user;

}
