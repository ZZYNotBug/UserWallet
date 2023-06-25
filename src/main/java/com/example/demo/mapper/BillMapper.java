package com.example.demo.mapper;

import com.example.demo.entity.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {

    // 查询账单明细
    List<Bill> selectBills(@Param("userId") int userId);

    // 增加账单
    int insertBill(Bill bill);


}
