package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {

    // 账单id
    private int id;
    // 用户id
    private int userId;
    // 钱包id
    private int walletId;
    // 当前余额
    private BigDecimal balance;
    // 金钱数额
    private BigDecimal money;
    // 金额状态
    private int moneyStatus;
    // 账单创建时间
    private Date createTime;


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

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(int moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", userId=" + userId +
                ", walletId=" + walletId +
                ", balance=" + balance +
                ", money=" + money +
                ", moneyStatus=" + moneyStatus +
                ", createTime=" + createTime +
                '}';
    }
}
