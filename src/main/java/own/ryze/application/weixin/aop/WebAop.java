package own.ryze.application.weixin.aop;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * web层切面
 * 
 * @author LCY
 *
 */
@Aspect
@Component
@Slf4j
public class WebAop
{
	@Pointcut("execution(public * own.ryze.application.weixin.web..*.*(..))")
	public void web()
	{

	}

	@Before("web()")
	public void doBefore(JoinPoint joinPoint) throws Throwable
	{

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.info("url : {}", request.getRequestURL());
		log.info("http_method : {}", request.getMethod());
		log.info("ip : {}", request.getRemoteAddr());
		log.info("class_method : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName());
		log.info("args : {}", Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "web()")
	public void doAfterReturning(Object ret) throws Throwable
	{
		// 处理完请求，返回内容
		log.info("response : {}", ret);
	}

	@Around("execution(public * own.ryze.application.weixin.web.*.*(..)) && args(..,bindingResult)")
	public String doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable
	{
		// 是否有错
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors)
		{
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError oe : allErrors)
			{
				return oe.getDefaultMessage();
			}
		}
		return (String) pjp.proceed();
	}
}
