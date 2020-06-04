package com.xinyuzang.game.domain.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/4 15:26
 * @Copyright: 2020 Hundsun All rights reserved.
 */
@Data
public class BaseRequest {

    @NotNull(message = "用户未登录")
    private Integer userId;
}
