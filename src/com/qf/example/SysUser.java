package com.qf.example;

import java.util.Date;

public class SysUser {
    private Integer uid;
    private String uname;
    private String password;
    private String phone;
    private Integer status;
    private Date createDateTime;

    public SysUser() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    //public static final int COL_UID= Integer.valueOf("uid");
    public static final String COL_UNAME="uname";
    public static final String COL_PASSWORD="password";
    public static final String COL_PHONE="phone";
    //public static final int COL_STATUS= Integer.parseInt("status");


}
