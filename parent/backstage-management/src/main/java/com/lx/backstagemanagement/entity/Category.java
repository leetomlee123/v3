package com.lx.backstagemanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class Category implements Serializable{
    @Id
    private String cid;
    private String cname;

    public Category(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Category() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
