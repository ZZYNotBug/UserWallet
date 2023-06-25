package com.example.demo.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.Service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/userWallet")
public class UserWalletController {

    @Autowired
    private UserWalletService userWalletService;

    /**
     * 查询余额的接口，userId为用户id
     */
    @GetMapping("/selectBalance/{userId}")
    public String getBalance(@PathVariable int userId) {
        // 获取余额
        BigDecimal balance = userWalletService.selectBalance(userId);
        JSONObject json = new JSONObject();
        // 判断余额是否为 null
        // 如果为 null 则获取余额失败
        if (balance == null) {
            json.put("code", "fail");
            json.put("message", "获取余额失败，用户不存在");
        }else {
            // 不为空则获取余额成功
            json.put("code", "success");
            json.put("userId", userId);
            json.put("balance", balance);
        }
        return json.toString();
    }


}
