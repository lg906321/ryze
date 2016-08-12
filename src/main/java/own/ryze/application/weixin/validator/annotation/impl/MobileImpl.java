package own.ryze.application.weixin.validator.annotation.impl;

import static own.ryze.application.weixin.util.StringUtil.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import own.ryze.application.weixin.validator.annotation.Mobile;

/**
 * 手机号验证实现
 * 
 * @author LCY
 *
 */
public class MobileImpl implements ConstraintValidator<Mobile, String>
{
	@Override
	public void initialize(Mobile mobile)
	{
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		return validate(value, isNotEmpty,isMobile);
	}

}
