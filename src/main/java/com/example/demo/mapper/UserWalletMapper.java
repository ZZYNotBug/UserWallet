package com.example.demo.mapper;

import com.example.demo.entity.UserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface UserWalletMapper {

    // 查询账户
    UserWallet selectUserWallet(@Param("userId") int userId);

    // 新增账户
    int updateBalance(@Param("userId") int userId, @Param("balance") BigDecimal balance);

}
