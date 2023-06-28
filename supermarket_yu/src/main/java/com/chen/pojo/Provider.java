package com.chen.pojo;/*
 *

 */

import java.util.Date;
public class Provider {
    private Integer id;   //id
    private String proCode; //サプライヤコード
    private String proName; //サプライヤ名称
    private String proDesc; //サプライヤ描述
    private String proContact; //サプライヤ連絡先
    private String proPhone; //サプライヤ电话
    private String proAddress; //サプライヤ地址
    private String proFax; //サプライヤ传真
    private Integer createdBy; //创建者
    private Date creationDate; //作成時間
    private Integer modifyBy; //更新者
    private Date modifyDate;//更新时间

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProCode() {
        return proCode;
    }
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProDesc() {
        return proDesc;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
    public String getProContact() {
        return proContact;
    }
    public void setProContact(String proContact) {
        this.proContact = proContact;
    }
    public String getProPhone() {
        return proPhone;
    }
    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }
    public String getProAddress() {
        return proAddress;
    }
    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }
    public String getProFax() {
        return proFax;
    }
    public void setProFax(String proFax) {
        this.proFax = proFax;
    }
    public Integer getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Integer getModifyBy() {
        return modifyBy;
    }
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }
    public Date getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}