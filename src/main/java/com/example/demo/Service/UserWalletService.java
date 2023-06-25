package com.example.demo.Service;

import com.example.demo.entity.UserWallet;
import com.example.demo.mapper.UserWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserWalletService {

    @Autowired
    private UserWalletMapper userWalletMapper;

    /**
     * 查询余额
      */
    public BigDecimal selectBalance(int userId) {

        // 判断该用户钱包是否存在
        UserWallet userWallet = userWalletMapper.selectUserWallet(userId);
        // 如果为空用户不存在
        if (userWallet == null) {
            return null;
        }
        // 如果用户不为空，则获取余额
        return userWallet.getBalance();

    }


}
