package com.board.backend.domain.User;

import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String userName;

    private String password;

    private String email;

    @CreatedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    public User(){
    }

    public User(Claims claims) {
        this.userId = claims.get("userId").toString();
        this.userName = claims.get("userName").toString();
    }
}
