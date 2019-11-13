package com.czw.ms.common.annotation;

import com.czw.ms.common.selector.MsCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 13:59
 * @UpdeteTime: 2019-10-09 13:59
 * @Description:使用注解注册到IOC容器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MsCloudApplicationSelector.class)
public @interface MsCloudApplication {
}
