package com.ZXKC.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Orders {
    private Long id;
    private String number;      // 订单号
    private Integer status;     // 状态 1:待制作 2:制作中 3:已完成
    private String tableNumber; // 桌号
    private LocalDateTime orderTime;
    private BigDecimal amount;
    private String remark;
}