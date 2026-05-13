package com.ZXKC.service;

import com.ZXKC.domain.Orders;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    /**
     * 条件分页查询
     * @param status 订单状态（1待制作，2制作中，3已完成）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 订单列表
     */
    PageInfo<Orders> pageQuery(Integer status, int pageNum, int pageSize);

    /**
     * 更新订单状态（例如：开始制作、完成制作）
     * @param id 订单ID
     * @param status 新状态
     */
    void updateStatus(Long id, Integer status);
}