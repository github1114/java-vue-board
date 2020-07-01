package com.board.backend.dto;

import com.board.backend.domain.Login.Login;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    @Nullable
    private Login login;

    @JsonProperty("uid")
    private long uid;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("accessToken")
    private String accessToken;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("accessTokenExpiredDate")
    private Date accessTokenExpiredDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastLoginDate")
    private Date lastLoginDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastLoginFailDate")
    private Date lastLoginFailDate;

    public Login toEntity(){
        this.login = new Login();
        this.login.setUserId(this.userId);
        this.login.setAccessToken(this.accessToken);
        this.login.setAccessTokenExpiredDate(this.accessTokenExpiredDate);
        this.login.setLastLoginDate(this.lastLoginDate);
        this.login.setLastLoginFailDate(this.lastLoginFailDate);
        return this.login;
    }
}
