package com.example.jpademo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "lbk_ad")
public class LbkAd {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imgUrl;
    private String link;
    private String content;
    private Integer rank;
    private Integer status;


}
