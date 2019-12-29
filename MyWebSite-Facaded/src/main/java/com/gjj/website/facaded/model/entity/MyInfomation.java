package com.gjj.website.facaded.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author :
 * @since 2019/12/26 0:31
 */
@Data
@Table(name = "my_infomation")
public class MyInfomation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "live_place")
    private String livePlace;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


}
