package com.example.demo;


import com.example.demo.mapper.UserWalletMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWalletTest {

    @Autowired
    private UserWalletMapper userWalletMapper;

    /**
     * 测试查询用户钱包
     */
    @Test
    public void selectUserWallet() {
        System.out.println(userWalletMapper.selectUserWallet(101));
    }

    /**
     * 测试更新用户钱包余额
     */
    @Test
    public void updateBalance() {

        userWalletMapper.updateBalance(101,new BigDecimal(100.00));

        System.out.println(userWalletMapper.selectUserWallet(101));


    }



}
