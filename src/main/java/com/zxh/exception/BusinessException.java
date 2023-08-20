package com.zxh.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 17:32
 *
 * 自定义运行时异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
