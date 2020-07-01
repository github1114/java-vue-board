package com.board.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdDate")
    private Date createdDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("modifyDate")
    private Date modifyDate;

}
