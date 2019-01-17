package com.example.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "guide_plan")
public class GuidePlan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer memberId;
    private Integer lable;
    private String planName;
    private String planImg;
    private Double price;
    private String planDesc;
    private Integer recommend;
    private String planDetail;
    private Double totolBudget;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;



}
