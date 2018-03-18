package com.lx.backstagemanagement.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {
    @Id
    private String itemid;//订单项的id
    private int count;//订单项内商品的购买数量
    private double subtotal;//订单项小计
    private String pid;//订单项内部的商品
    private String oid;//该订单项属于哪个订单


    public OrderItem() {

    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
