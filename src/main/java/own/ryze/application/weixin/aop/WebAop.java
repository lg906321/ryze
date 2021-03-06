package own.ryze.application.weixin.aop;

import java.util.Arrays;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import own.ryze.application.weixin.common.PortReturn;

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
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void web()
	{

	}

	@Before("web()")
	public void doBefore(JoinPoint joinPoint) throws Throwable
	{
		// 接收到请求，记录请求内容
		final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		final HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.info("url : {}", request.getRequestURL());
		log.info("请求方式 : {}", request.getMethod());
		log.info("IP地址 : {}", request.getRemoteAddr());
		log.info("方法 : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		log.info("参数 : {}", Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "web()")
	public void doAfterReturning(Object ret) throws Throwable
	{
		// 处理完请求，返回内容
		log.info("响应 : {}", ret);
	}

	@Around("web() && args(*,br,..)")
	public Object doAround(ProceedingJoinPoint pjp, BindingResult br) throws Throwable
	{
		final boolean hasErrors = br.hasErrors();
		if (hasErrors)
		{
			final ObjectError objectError = br.getAllErrors().stream().findFirst().get();
			final JSONObject json = JSON.parseObject(objectError.getDefaultMessage());

			return PortReturn.returnJSON(json.getInteger("code"), json.getString("msg"));
		}
		return pjp.proceed();
	}
}
