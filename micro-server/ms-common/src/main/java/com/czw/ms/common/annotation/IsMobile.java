package com.czw.ms.common.annotation;

import com.czw.ms.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 15:59
 * @UpdeteTime: 2019-10-14 15:59
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MobileValidator.class)
public @interface IsMobile {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
