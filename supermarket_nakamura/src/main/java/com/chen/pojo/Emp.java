package com.chen.pojo;

import java.util.Date;

public class Emp {
    private Integer id;
    private String empCode;
    private String empName;  
	private String rank;
    private Integer age;
    private String gender;
    private String phone;
    private String mail;
    private String IDCard;
    private String picture;
    private String address;
    private Integer createdBy; 
    private Date creationDate;
    private Integer modifyBy;  
    private Date modifyDate;
    
    private Integer empRole;
    private String empRoleName;    //用户角色名称
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		if(gender == 1) {
			this.gender= "男性";
		}
		else if(gender == 2) {
			this.gender = "女性";
		}
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	public Integer getEmpRole() {
		return empRole;
	}
	public void setEmpRole(Integer empRole) {
		this.empRole = empRole;
	}

	public String getEmpRoleName() {
		return empRoleName;
	}
	public void setEmpRoleName(String empRoleName) {
		this.empRoleName = empRoleName;
	}
}