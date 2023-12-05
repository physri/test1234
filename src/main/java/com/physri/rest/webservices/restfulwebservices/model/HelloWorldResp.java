package com.physri.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties("quantity")
public class HelloWorldResp {
//    @JsonProperty("My_HW_Message")
    private String msg;
//    @JsonProperty("My_HW_Status")
    private String status;
//    @JsonIgnore
//    @JsonProperty("HW_Qty")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
