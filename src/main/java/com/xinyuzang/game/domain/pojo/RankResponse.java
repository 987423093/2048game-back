package com.xinyuzang.game.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhoutao
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
public class RankResponse {

    /**
     * 得分
     */
    private Integer score;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 创建时间
     */
    private String createTime;
}
