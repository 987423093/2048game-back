package com.xinyuzang.game.utils;

import com.xinyuzang.game.common.enums.ExceptionEnum;
import com.xinyuzang.game.config.exception.MyException;
import org.springframework.beans.BeanUtils;

/**
 * @author zhoutao
 * @date 2020/6/3
 */
public class CopyUtils {

    /**
     * 拷贝属性
     * @param source
     * @param targetClazz
     * @return
     */
    public static <T> T copyProperties(Object source, Class targetClazz) {

        T target = null;
        try {
            target = (T) targetClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MyException(ExceptionEnum.SERVER_ERROR);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
