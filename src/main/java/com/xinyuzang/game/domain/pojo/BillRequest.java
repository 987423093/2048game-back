package com.xinyuzang.game.domain.pojo;

import com.xinyuzang.game.domain.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author zhoutao
 * @date 2020/6/16
 */
@Data
public class BillRequest extends BaseRequest {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 账单时间
     */
    @NotEmpty(message = "必须有账单时间")
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
