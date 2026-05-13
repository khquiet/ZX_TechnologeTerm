package com.ZXKC.controller;

import com.ZXKC.domain.Orders;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController // 标记这是一个控制器，且返回的是数据(JSON)
@RequestMapping("/orders") // 统一访问路径
public class OrderController {

    // 模拟数据库数据（因为没有连真实数据库，先写死数据让你看到效果）
    @GetMapping("/kitchen")
    public List<Orders> getKitchenOrders(@RequestParam(required = false) Integer status) {
        List<Orders> list = new ArrayList<>();

        // 模拟订单1
        Orders o1 = new Orders();
        o1.setId(1L);
        o1.setNumber("202405210001");
        o1.setTableNumber("3号桌");
        o1.setOrderTime(LocalDateTime.now());
        o1.setAmount(new BigDecimal("58.00"));
        o1.setRemark("少辣");
        o1.setStatus(1); // 1 代表待制作

        // 模拟订单2
        Orders o2 = new Orders();
        o2.setId(2L);
        o2.setNumber("202405210002");
        o2.setTableNumber("5号桌");
        o2.setOrderTime(LocalDateTime.now().minusMinutes(10));
        o2.setAmount(new BigDecimal("128.00"));
        o2.setRemark("不要香菜");
        o2.setStatus(2); // 2 代表制作中

        list.add(o1);
        list.add(o2);

        return list;
    }
}