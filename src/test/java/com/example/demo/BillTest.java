package com.example.demo;

import com.example.demo.Service.BillService;
import com.example.demo.entity.Bill;
import com.example.demo.mapper.BillMapper;
import com.example.demo.mapper.UserWalletMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillTest {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private UserWalletMapper userWalletMapper;

    /**
     * 测试查询账单明细
     */
    @Test
    public void selectBills() {
        List<Bill> billList = new ArrayList<>();
        // 获取 101 用户的账单明细
        billList = billMapper.selectBills(101);
        for (Bill bill : billList) {
            System.out.println(bill);
        }

    }

    /**
     * 测试增加账单
     */
    @Test
    public void addBill() {
        Bill bill = new Bill();
        // 101 用户消费了十元
        bill.setUserId(101);
        bill.setWalletId(12);
        bill.setBalance(userWalletMapper.selectUserWallet(101).getBalance());
        bill.setMoney(new BigDecimal(10.00));
        bill.setMoneyStatus(2);
        bill.setCreateTime(new Date());
        billMapper.insertBill(bill);
    }

    /**
     * 测试 Service层消费
     * 101 用户余额 150.00
     * 消费 10.00
     * 余额更新为 140.00
     * 账单增加新数据 7,101,12,150.00,10.00,2,时间
     * 输出 success
     */
    @Test
    public void consume() {
        String result = billService.consume(101,new BigDecimal(10.00));
        System.out.println(result);
    }

    /**
     * 测试 Service层消费失败事务回滚
     * 101 用户余额 150.00
     * 消费 160.00
     * 新余额小于0，发生错误，事务回滚
     * 账户余额 150.00
     * 账单不增加
     * 输出 fail
     */
    @Test
    public void consumeRollback() {
        String result = billService.consume(101,new BigDecimal(160.00));
        System.out.println(result);
    }

    /**
     * 测试 Service层退款
     * 102 用户余额 50.00
     * 退款 160.00
     * 余额更新为 210.00
     * 账单增加新数据 9,102,13,50.00,160.00,1,时间
     * 输出 success
     */
    @Test
    public void refund() {
        String result = billService.refund(102,new BigDecimal(160.00));
        System.out.println(result);
    }

}
