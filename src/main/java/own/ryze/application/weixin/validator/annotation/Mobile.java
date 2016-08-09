package own.ryze.application.weixin.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import own.ryze.application.weixin.validator.annotation.impl.MobileImpl;

/**
 * 手机号校验
 * @author LCY
 *
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileImpl.class)
public @interface Mobile
{
	String message();  
    Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
}
