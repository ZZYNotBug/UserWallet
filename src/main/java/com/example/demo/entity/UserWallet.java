package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserWallet {

    // 用户钱包id
    private int id;
    // 用户id
    private int userId;
    // 余额
    private BigDecimal balance;
    // 金额更新时间
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserWallet{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", updateTime=" + updateTime +
                '}';
    }
}
