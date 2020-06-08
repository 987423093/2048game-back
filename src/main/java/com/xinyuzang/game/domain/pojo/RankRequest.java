package com.xinyuzang.game.domain.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhoutao
 * @date 2020/6/8
 */
@Data
public class RankRequest {

    /**
     * 得分
     */
    @NotNull(message = "得分不能为空")
    private Integer score;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickName;

    /**
     * 创建时间
     */
    @NotEmpty(message = "创建时间不能为空")
    private String createTime;
}
