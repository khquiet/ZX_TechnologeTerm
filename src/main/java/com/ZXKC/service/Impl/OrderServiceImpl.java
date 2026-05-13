package com.ZXKC.service.Impl;

import com.ZXKC.domain.Orders;
import com.ZXKC.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 模拟数据库数据源
     * 实际开发中，这里应该是 @Autowired OrderMapper orderMapper;
     */
    private List<Orders> mockDatabase = new ArrayList<>();

    // 构造方法：初始化一些假数据，方便你看效果
    public OrderServiceImpl() {
        Orders o1 = new Orders();
        o1.setId(1L);
        o1.setNumber("202405210001");
        o1.setTableNumber("3号桌");
        o1.setStatus(1); // 待制作
        o1.setOrderTime(LocalDateTime.now().minusMinutes(10));
        o1.setRemark("少辣");

        Orders o2 = new Orders();
        o2.setId(2L);
        o2.setNumber("202405210002");
        o2.setTableNumber("5号桌");
        o2.setStatus(1); // 待制作
        o2.setOrderTime(LocalDateTime.now().minusMinutes(5));
        o2.setRemark("");

        Orders o3 = new Orders();
        o3.setId(3L);
        o3.setNumber("202405210003");
        o3.setTableNumber("8号桌");
        o3.setStatus(2); // 制作中
        o3.setOrderTime(LocalDateTime.now().minusMinutes(20));

        mockDatabase.add(o1);
        mockDatabase.add(o2);
        mockDatabase.add(o3);
    }

    @Override
    public PageInfo<Orders> pageQuery(Integer status, int pageNum, int pageSize) {
        // 1. 开启分页（PageHelper会自动拦截下面的查询操作）
        PageHelper.startPage(pageNum, pageSize);

        // 2. 模拟从数据库查询数据
        List<Orders> result;
        if (status == null) {
            // 如果没传状态，查所有
            result = new ArrayList<>(mockDatabase);
        } else {
            // 根据状态过滤
            result = mockDatabase.stream()
                    .filter(order -> order.getStatus().equals(status))
                    .collect(Collectors.toList());
        }

        // 3. 封装成 PageInfo 返回（包含总记录数、总页数等信息）
        return new PageInfo<>(result);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        // 模拟更新逻辑
        for (Orders order : mockDatabase) {
            if (order.getId().equals(id)) {
                order.setStatus(status);
                System.out.println("订单 " + id + " 状态已更新为：" + status);
                return;
            }
        }
        throw new RuntimeException("订单ID不存在");
    }
}