package com.czw.ms.common.validator;

import com.czw.ms.common.annotation.IsMobile;
import com.czw.ms.common.entity.RegexpConstant;
import com.czw.ms.common.utils.FebsUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 16:05
 * @UpdeteTime: 2019-10-14 16:05
 * @Description:
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {
	@Override
	public void initialize(IsMobile isMobile){
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext context) {
		try{
			if (StringUtils.isBlank(s)){
				return true;
			}else {
				String regex= RegexpConstant.MOBILE_REG;
				return FebsUtil.match(regex,s);
			}
		}catch (Exception e){
			return false;
		}
	}
}
