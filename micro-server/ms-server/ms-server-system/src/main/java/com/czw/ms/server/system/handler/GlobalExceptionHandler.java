package com.czw.ms.server.system.handler;

import com.czw.ms.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 09:28
 * @UpdeteTime: 2019-10-09 09:28
 * @Description:
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
