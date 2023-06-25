package com.example.demo.Service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.UserWallet;
import com.example.demo.mapper.BillMapper;
import com.example.demo.mapper.UserWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private UserWalletMapper userWalletMapper;

    /**
     * 消费
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public String consume(int userId, BigDecimal money) {
        // 获取当前用户钱包
        UserWallet userWallet = userWalletMapper.selectUserWallet(userId);
        // 判断用户是否存在
        if(userWallet == null) {
            return "userNotExist";
        }
        // 获取当前余额
        BigDecimal nowBalance = userWallet.getBalance();
        // 计算得出新余额
        // 如果 newBalance 是负数，即当前余额小于消费金额
        BigDecimal newBalance = nowBalance.subtract(money);
        // newBalance.compareTo(new BigDecimal(0) 为 -1
        if(newBalance.compareTo(new BigDecimal(0)) < 0) {
            // 如果 newBalance 是负数，执行此代码，手动提交事务，进行回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "fail";
        }
        // 钱包更新余额
        userWalletMapper.updateBalance(userId,newBalance);
        // 增加消费明细
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setWalletId(userWallet.getId());
        bill.setBalance(nowBalance);
        bill.setMoney(money);
        bill.setMoneyStatus(2);
        bill.setCreateTime(new Date());
        billMapper.insertBill(bill);
        return "success";
    }

    /**
     * 退款
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public String refund(int userId, BigDecimal money) {
        // 获取当前用户钱包
        UserWallet userWallet = userWalletMapper.selectUserWallet(userId);
        // 判断用户是否存在
        if(userWallet == null) {
            return "userNotExist";
        }
        // 获取当前余额
        BigDecimal nowBalance = userWallet.getBalance();
        // 计算得出新余额
        BigDecimal newBalance = nowBalance.add(money);
        // 钱包更新余额
        userWalletMapper.updateBalance(userId,newBalance);
        // 测试事务回滚
        // int a = 1/0;
        // 增加消费明细
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setWalletId(userWallet.getId());
        bill.setBalance(nowBalance);
        bill.setMoney(money);
        bill.setMoneyStatus(1);
        bill.setCreateTime(new Date());
        billMapper.insertBill(bill);
        return "success";
    }

    /**
     * 查看金额变动明细
     */
    public List<Bill> selectBills(int userId) {
        List<Bill> billList = new ArrayList<>();
        billList = billMapper.selectBills(userId);
        return billList;
    }

}
