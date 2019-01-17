package com.example.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@DynamicInsert
@Table(name = "member_reservation")
public class MemberReservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer memberId;
    private Integer label;
    private Integer type;
    private String origin;
    private String destination;
    private Date beginDate;
    private Double budget;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;


}
