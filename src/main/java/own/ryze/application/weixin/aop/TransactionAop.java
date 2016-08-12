package own.ryze.application.weixin.aop;

import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 事务切面
 * @author LCY
 *
 */
@Aspect
@Component
@Slf4j
public class TransactionAop
{
	@Pointcut("@annotation(org.springframework.stereotype.Service)")
	public void service()
	{
		
	}
	
	@AdviceName("transactionAdvice")
	public void advice()
	{
		
	}
	
}
