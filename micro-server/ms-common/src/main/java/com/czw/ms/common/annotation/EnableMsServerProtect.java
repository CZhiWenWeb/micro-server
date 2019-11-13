package com.czw.ms.common.annotation;

import com.czw.ms.common.configure.MsServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 11:48
 * @UpdeteTime: 2019-10-09 11:48
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MsServerProtectConfigure.class)
public @interface EnableMsServerProtect {
}
