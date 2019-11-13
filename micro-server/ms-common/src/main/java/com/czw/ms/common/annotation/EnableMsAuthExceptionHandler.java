package com.czw.ms.common.annotation;

import com.czw.ms.common.configure.MsAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 17:34
 * @UpdeteTime: 2019-10-08 17:34
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MsAuthExceptionConfigure.class)
public @interface EnableMsAuthExceptionHandler {
}
