package com.board.backend.domain.Login;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    private String userId;

    private String accessToken;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date accessTokenExpiredDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginFailDate;

}
