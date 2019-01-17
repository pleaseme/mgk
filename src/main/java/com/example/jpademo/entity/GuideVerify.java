package com.example.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@DynamicInsert
@Table(name = "guide_verify")
public class GuideVerify {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer memberId;
    private Integer sex;
    private String school;
    private String major;
    private Date birthday;
    private String introduction;
    private String serviceArea;
    private String idcardImg;
    private String bankCard;
    private String phone;
    private String stuImg;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;




}
