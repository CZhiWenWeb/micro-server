package com.czw.ms.common.annotation;

import com.czw.ms.common.configure.MsOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 11:01
 * @UpdeteTime: 2019-10-09 11:01
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MsOAuth2FeignConfigure.class)
public @interface EnableMsAuth2FeignClient {
}
