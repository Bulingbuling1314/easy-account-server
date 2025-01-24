package com.example.easyaccount.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BillParam {
    private Long id;  // 账单唯一标识
    private BigDecimal amount;  // 账单金额
    private Long categoryId;
    private Integer type;
    private String year;
    private String month;
    private String day;
}
