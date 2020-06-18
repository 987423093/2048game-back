package com.xinyuzang.game.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author XinYuZang
 * @since 2020-06-16
 */
public class Bill implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(BigDecimal breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public BigDecimal getLunchPrice() {
        return lunchPrice;
    }

    public void setLunchPrice(BigDecimal lunchPrice) {
        this.lunchPrice = lunchPrice;
    }

    public BigDecimal getDinnerPrice() {
        return dinnerPrice;
    }

    public void setDinnerPrice(BigDecimal dinnerPrice) {
        this.dinnerPrice = dinnerPrice;
    }

    public BigDecimal getAfternoonTeaPrice() {
        return afternoonTeaPrice;
    }

    public void setAfternoonTeaPrice(BigDecimal afternoonTeaPrice) {
        this.afternoonTeaPrice = afternoonTeaPrice;
    }

    public BigDecimal getPartyActivityPrice() {
        return partyActivityPrice;
    }

    public void setPartyActivityPrice(BigDecimal partyActivityPrice) {
        this.partyActivityPrice = partyActivityPrice;
    }

    public BigDecimal getBedtimeSnackPrice() {
        return bedtimeSnackPrice;
    }

    public void setBedtimeSnackPrice(BigDecimal bedtimeSnackPrice) {
        this.bedtimeSnackPrice = bedtimeSnackPrice;
    }

    public BigDecimal getAipPrice() {
        return aipPrice;
    }

    public void setAipPrice(BigDecimal aipPrice) {
        this.aipPrice = aipPrice;
    }

    public BigDecimal getOnlineShoppingPrice() {
        return onlineShoppingPrice;
    }

    public void setOnlineShoppingPrice(BigDecimal onlineShoppingPrice) {
        this.onlineShoppingPrice = onlineShoppingPrice;
    }

    public BigDecimal getOtherConsumptionPrice() {
        return otherConsumptionPrice;
    }

    public void setOtherConsumptionPrice(BigDecimal otherConsumptionPrice) {
        this.otherConsumptionPrice = otherConsumptionPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
        "id=" + id +
        ", userId=" + userId +
        ", billDate=" + billDate +
        ", breakfastPrice=" + breakfastPrice +
        ", lunchPrice=" + lunchPrice +
        ", dinnerPrice=" + dinnerPrice +
        ", afternoonTeaPrice=" + afternoonTeaPrice +
        ", partyActivityPrice=" + partyActivityPrice +
        ", bedtimeSnackPrice=" + bedtimeSnackPrice +
        ", aipPrice=" + aipPrice +
        ", onlineShoppingPrice=" + onlineShoppingPrice +
        ", otherConsumptionPrice=" + otherConsumptionPrice +
        "}";
    }
}
