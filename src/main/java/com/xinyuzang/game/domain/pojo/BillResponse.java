package com.xinyuzang.game.domain.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoutao
 * @date 2020/6/16
 */
@Data
public class BillResponse {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 账单时间
     */
    private String billDate;

    /**
     * 早饭金额
     */
    private BigDecimal breakfastPrice;

    /**
     * 午饭金额
     */
    private BigDecimal lunchPrice;

    /**
     * 晚饭金额
     */
    private BigDecimal dinnerPrice;

    /**
     * 水果/下午茶
     */
    private BigDecimal afternoonTeaPrice;

    /**
     * 聚餐/活动
     */
    private BigDecimal partyActivityPrice;

    /**
     * 夜宵
     */
    private BigDecimal bedtimeSnackPrice;

    /**
     * 基金定投
     */
    private BigDecimal aipPrice;

    /**
     * 网购衣物/化妆品/零食等
     */
    private BigDecimal onlineShoppingPrice;

    /**
     * 其他消费
     */
    private BigDecimal otherConsumptionPrice;
}
