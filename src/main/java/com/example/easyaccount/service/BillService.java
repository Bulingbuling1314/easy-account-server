package com.example.easyaccount.service;

import cn.hutool.json.JSONUtil;
import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.BillEntity;
import com.example.easyaccount.entity.param.BillParam;
import com.example.easyaccount.entity.result.BillResult;
import com.example.easyaccount.repository.BillReposotory;
import com.example.easyaccount.tools.RedisService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BillService {
    @Autowired
    private BillReposotory billReposotory;

    @Autowired
    private RedisService redisService;

    public ResultMap save(BillEntity bill) {
        bill.setUserId(getLongUserId());
        return ResultMap.ok(billReposotory.save(bill));
    }


    public ResultMap find(BillParam bill) {
        String year =  !StringUtils.isEmpty(bill.getYear()) ? String.valueOf(bill.getYear()) : null;
        String month = !StringUtils.isEmpty(bill.getMonth()) ? String.format("%02d", Integer.parseInt(bill.getMonth())) : null;
        String day = !StringUtils.isEmpty(bill.getDay()) ? String.format("%02d", Integer.parseInt(bill.getDay())) : null;

        List<BillResult> billList = billReposotory.find(getLongUserId(), year, month, day, bill.getType(), bill.getCategoryId());

        Map<String, Object> result = new HashMap<>();
        result.put("list", billList);
        if(year != null) {
            result.put("incomeTotalAmountByYear", billReposotory.findAmountByYear(getLongUserId(), year, bill.getType()).get("incomeTotalAmount"));
            result.put("expenditureTotalAmountYear", billReposotory.findAmountByYear(getLongUserId(), year, bill.getType()).get("expenditureTotalAmount"));
        }
        if(month != null) {
            result.put("incomeTotalAmountByMonth", billReposotory.findAmountByMonth(getLongUserId(), year, month, bill.getType()).get("incomeTotalAmount"));
            result.put("expenditureTotalAmountMonth", billReposotory.findAmountByMonth(getLongUserId(), year, month, bill.getType()).get("expenditureTotalAmount"));
        }
        if(day != null) {
            result.put("incomeTotalAmountByDay", billReposotory.findAmountByDay(getLongUserId(), year, month, day, bill.getType()).get("incomeTotalAmount"));
            result.put("expenditureTotalAmountDay", billReposotory.findAmountByDay(getLongUserId(), year, month, day, bill.getType()).get("expenditureTotalAmount"));
        }

        return ResultMap.ok(result);
    }


    public ResultMap delete(BillEntity bill) {
        BillEntity billData = billReposotory.findById(bill.getId());
        billReposotory.delete(billData);
        return ResultMap.ok("操作成功");
    }

    private Long getLongUserId() {
        Map<String, Object> userInfo = JSONUtil.parseObj(redisService.get("userInfo"));
        return Long.valueOf(userInfo.get("id").toString());
    }
}
