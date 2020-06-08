package com.xinyuzang.game.config.exception;

import com.xinyuzang.game.common.enums.ExceptionEnum;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 17:08
 * @Copyright: 2020 Hundsun All rights reserved.
 */
public class MyException extends RuntimeException {

    public MyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }

    public MyException(String message) {
        super(message);
    }
}
