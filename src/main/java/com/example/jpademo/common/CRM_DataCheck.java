package com.example.jpademo.common;

public class CRM_DataCheck {


    //由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间。>=6, <=16
    String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

     public Boolean checkData(String date){
        return date.matches(regex);
    }



}
