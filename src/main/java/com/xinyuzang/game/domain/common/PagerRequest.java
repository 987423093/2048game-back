package com.xinyuzang.game.domain.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhoutao
 * @date 2020/6/8
 */
@Data
public class PagerRequest {

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    /**
     * 一页大小
     */
    @NotNull(message = "一页大小不能为空")
    private Integer pageSize;
}
