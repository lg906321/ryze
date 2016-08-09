package own.ryze.application.weixin.validator.annotation.impl;

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
	private static final String MOBILE = "^(1[3,5,8,7]{1}[\\d]{9})|(((400)-(\\d{3})-(\\d{4}))|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$";

	@Override
	public void initialize(Mobile constraintAnnotation)
	{
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		try
		{
			if (value.matches(MOBILE)) return true;
		}
		catch (Exception e)
		{
			return false;
		}
		return false;
	}

}
