package com.example.demo.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.Service.BillService;
import com.example.demo.Service.UserWalletService;
import com.example.demo.entity.Bill;
import com.example.demo.entity.UserWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private UserWalletService userWalletService;

    /**
     * 消费
     */
    @GetMapping("/consume/{userId}/{money}")
    public String consume(@PathVariable("userId") int userId, @PathVariable("money") BigDecimal money) {
        // 获取字符串结果
        String result = billService.consume(userId, money);
        JSONObject json = new JSONObject();
        // 如果 result = 'userNotExist'，用户不存在
        if(result.equals("userNotExist")) {
            json.put("code", "userNotExist");
            json.put("message", "用户不存在");
        }
        // 如果 result = 'fail'，余额不足，消费失败
        else if(result.equals("fail")) {
            json.put("code", "fail");
            json.put("message", "余额不足，消费失败");
        }
        // 如果 result = 'success'，消费成功
        else if(result.equals("success")) {
            json.put("code", "success");
            json.put("message", "消费成功");
        }
        return json.toString();
    }

    /**
     * 退款
     */
    @GetMapping("/refund/{userId}/{money}")
    public String refund(@PathVariable("userId") int userId, @PathVariable("money") BigDecimal money) {
        // 获取字符串结果
        String result = billService.refund(userId, money);
        JSONObject json = new JSONObject();
        // 如果 result = 'userNotExist'，用户不存在
        if(result.equals("userNotExist")) {
            json.put("code", "userNotExist");
            json.put("message", "用户不存在");
        }
        // 如果 result = 'success'，退款成功
        else if(result.equals("success")) {
            json.put("code", "success");
            json.put("message", "退款成功");
        }
        else {
            json.put("code", "failed");
            json.put("message", "退款失败");
        }
        return json.toString();
    }

    /**
     * 查询用户的账单明细
     */
    @GetMapping("/selectBills/{userId}")
    public String selectBills(@PathVariable("userId") int userId) {
        JSONObject json = new JSONObject();
        // 判断用户是否存在
        BigDecimal balance = userWalletService.selectBalance(userId);
        // 如果为 null 则用户不存在
        if (balance == null) {
            json.put("code", "fail");
            json.put("message", "用户不存在");
        }else {
            List<Bill> billList = new ArrayList<>();
            billList = billService.selectBills(userId);
            json.put("code", "success");
            json.put("message", billList);
        }
        return json.toString();
    }

}
