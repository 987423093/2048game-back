package com.xinyuzang.game.controller;

import com.xinyuzang.game.common.constant.RedisConstant;
import com.xinyuzang.game.config.exception.MyException;
import com.xinyuzang.game.domain.common.ApiResult;
import com.xinyuzang.game.domain.common.PagerRequest;
import com.xinyuzang.game.domain.pojo.RankRequest;
import com.xinyuzang.game.domain.pojo.RankResponse;
import com.xinyuzang.game.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhoutao
 * @date 2020/6/8
 */
@RestController
@RequestMapping("/rank/")
public class RankController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列出历史的得分
     * 每次游玩都上一个榜
     * @return
     */
    @PostMapping("listHistoryRank")
    public ApiResult<List<RankResponse>> listHistoryRank(@RequestBody @Validated PagerRequest pagerRequest) {

        Set<String> rankSet;
        try (Jedis jedis = redisUtil.getJedisPool().getResource()) {
            rankSet = jedis.zrevrange(RedisConstant.HISTORY_RANK, 0, -1);
        }catch (Exception e) {
            throw new MyException("获取历史排行失败");
        }
        List<String> ranks = new ArrayList<>(rankSet);
        List<RankResponse> rankResponses = new ArrayList<>();
        ranks.stream().forEach(item -> {
            String[] str = item.split(",");
            RankResponse rankResponse = new RankResponse(Integer.valueOf(str[0]), str[1], str[2]);
            rankResponses.add(rankResponse);
        });
        int start = pagerRequest.getPageSize() * (pagerRequest.getPageNum() - 1);
        int end = start + pagerRequest.getPageSize();
        return ApiResult.success(rankResponses.subList(start, Math.min(end, rankResponses.size())));
    }

    /**
     * 添加历史排行信息
     * @param rankRequest
     * @return
     */
    @PostMapping("addHistoryRank")
    public ApiResult addHistoryRank(@RequestBody @Validated RankRequest rankRequest) {

        try (Jedis jedis = redisUtil.getJedisPool().getResource()) {
            jedis.zadd(RedisConstant.HISTORY_RANK, rankRequest.getScore()
                    , rankRequest.getScore()
                    + "," + rankRequest.getNickName()
                    + "," + rankRequest.getCreateTime());
        }catch (Exception e) {
            throw new MyException("添加历史排行信息失败");
        }
        return ApiResult.success();
    }
}
