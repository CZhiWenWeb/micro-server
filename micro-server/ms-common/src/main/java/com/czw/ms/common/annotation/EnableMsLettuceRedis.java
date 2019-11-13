package com.czw.ms.common.annotation;

import com.czw.ms.common.configure.MsLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 15:03
 * @UpdeteTime: 2019-10-10 15:03
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MsLettuceRedisConfigure.class)
public @interface EnableMsLettuceRedis {

}
