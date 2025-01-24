package com.example.easyaccount.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class BillResult {
    private Long id;  // 账单唯一标识
    private BigDecimal amount;  // 账单金额
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;  // 账单日期
    private String description;  // 描述信息
    private String categoryName;
//    private BigDecimal incomeTotalAmount;
//    private BigDecimal expenditureTotalAmount;
}
