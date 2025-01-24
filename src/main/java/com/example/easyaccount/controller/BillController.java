package com.example.easyaccount.controller;

import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.BillEntity;
import com.example.easyaccount.entity.param.BillParam;
import com.example.easyaccount.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMap save(@RequestBody BillEntity data) {
        return billService.save(data);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResultMap find(@RequestBody BillParam data) {
        return billService.find(data);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultMap find(@RequestBody BillEntity data) {
        return billService.delete(data);
    }
}
