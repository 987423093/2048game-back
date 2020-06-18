package com.xinyuzang.game.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinyuzang.game.domain.common.ApiResult;
import com.xinyuzang.game.domain.entity.Bill;
import com.xinyuzang.game.domain.entity.User;
import com.xinyuzang.game.domain.pojo.BillRequest;
import com.xinyuzang.game.domain.pojo.BillResponse;
import com.xinyuzang.game.service.BillService;
import com.xinyuzang.game.service.UserService;
import com.xinyuzang.game.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XinYuZang
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/bill/")
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;

    /**
     * 添加账单
     * @param billRequest
     * @return
     */
    @PostMapping("modifyBill")
    public ApiResult modifyBill(@RequestBody @Validated BillRequest billRequest) {

        //先根据userId和billDate查一下有没有，没有就插入，有就修改
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", billRequest.getUserId());
        queryWrapper.eq("bill_date", billRequest.getBillDate());
        Bill bill = billService.getOne(queryWrapper);
        if (bill == null) {
            bill = new Bill();
            bill.setUserId(billRequest.getUserId());
            bill.setBillDate(billRequest.getBillDate());
            bill.setAfternoonTeaPrice(Optional.ofNullable(billRequest.getAfternoonTeaPrice()).orElse(BigDecimal.ZERO));
            bill.setBreakfastPrice(Optional.ofNullable(billRequest.getBreakfastPrice()).orElse(BigDecimal.ZERO));
            bill.setAipPrice(Optional.ofNullable(billRequest.getAipPrice()).orElse(BigDecimal.ZERO));
            bill.setBedtimeSnackPrice(Optional.ofNullable(billRequest.getBedtimeSnackPrice()).orElse(BigDecimal.ZERO));
            bill.setDinnerPrice(Optional.ofNullable(billRequest.getDinnerPrice()).orElse(BigDecimal.ZERO));
            bill.setLunchPrice(Optional.ofNullable(billRequest.getLunchPrice()).orElse(BigDecimal.ZERO));
            bill.setOnlineShoppingPrice(Optional.ofNullable(billRequest.getOnlineShoppingPrice()).orElse(BigDecimal.ZERO));
            bill.setOtherConsumptionPrice(Optional.ofNullable(billRequest.getOtherConsumptionPrice()).orElse(BigDecimal.ZERO));
            bill.setPartyActivityPrice(Optional.ofNullable(billRequest.getPartyActivityPrice()).orElse(BigDecimal.ZERO));
            billService.save(bill);
        } else {
            Bill billParam = CopyUtils.copyProperties(billRequest, Bill.class);
            billService.update(billParam, queryWrapper);
        }
        return ApiResult.success();
    }

    /**
     * 列出某日的账单
     * @param billRequest
     * @return
     */
    @PostMapping("listBillOnDay")
    public ApiResult<List<BillResponse>> listBillOnDay(@RequestBody @Validated BillRequest billRequest) {

        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bill_date", billRequest.getBillDate());
        List<Bill> bills = billService.list(queryWrapper);
        List<BillResponse> billResponses = new ArrayList<>();
        for (Bill bill : bills) {
            BillResponse billResponse = CopyUtils.copyProperties(bill, BillResponse.class);
            User user = userService.getById(bill.getUserId());
            billResponse.setNickName(user != null ? user.getNickName() : "");
            billResponses.add(billResponse);
        }
        return ApiResult.success(billResponses);
    }
}

