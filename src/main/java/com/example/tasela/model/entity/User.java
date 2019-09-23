package com.example.tasela.model.entity;

import java.util.Date;

public class User {
    private Long id;

    private String userCode;

    private String userName;

    private String userPass;

    private String userLoginName;

    private String userAlipayId;

    private String userWechaId;

    private Date updateTime;

    private Date creatTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName == null ? null : userLoginName.trim();
    }

    public String getUserAlipayId() {
        return userAlipayId;
    }

    public void setUserAlipayId(String userAlipayId) {
        this.userAlipayId = userAlipayId == null ? null : userAlipayId.trim();
    }

    public String getUserWechaId() {
        return userWechaId;
    }

    public void setUserWechaId(String userWechaId) {
        this.userWechaId = userWechaId == null ? null : userWechaId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", userPass=").append(userPass);
        sb.append(", userLoginName=").append(userLoginName);
        sb.append(", userAlipayId=").append(userAlipayId);
        sb.append(", userWechaId=").append(userWechaId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", creatTime=").append(creatTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPass() == null ? other.getUserPass() == null : this.getUserPass().equals(other.getUserPass()))
            && (this.getUserLoginName() == null ? other.getUserLoginName() == null : this.getUserLoginName().equals(other.getUserLoginName()))
            && (this.getUserAlipayId() == null ? other.getUserAlipayId() == null : this.getUserAlipayId().equals(other.getUserAlipayId()))
            && (this.getUserWechaId() == null ? other.getUserWechaId() == null : this.getUserWechaId().equals(other.getUserWechaId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreatTime() == null ? other.getCreatTime() == null : this.getCreatTime().equals(other.getCreatTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPass() == null) ? 0 : getUserPass().hashCode());
        result = prime * result + ((getUserLoginName() == null) ? 0 : getUserLoginName().hashCode());
        result = prime * result + ((getUserAlipayId() == null) ? 0 : getUserAlipayId().hashCode());
        result = prime * result + ((getUserWechaId() == null) ? 0 : getUserWechaId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreatTime() == null) ? 0 : getCreatTime().hashCode());
        return result;
    }
}