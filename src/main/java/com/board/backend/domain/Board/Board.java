package com.board.backend.domain.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;

    private String userId;

    private String title;

    private String context;

    @CreatedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;


}
