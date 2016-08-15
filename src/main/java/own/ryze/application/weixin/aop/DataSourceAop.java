package own.ryze.application.weixin.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import own.ryze.application.weixin.config.datasource.DataSourceContextHolder;

/**
 * 数据源切面
 * @author LCY
 *
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop
{
	@Before("execution(* own.ryze.application.weixin.persistent.dao..*.find*(..))")
	public void setReadDataSource()
	{
		DataSourceContextHolder.read();
        log.info("数据源切换到：从读库");
	}
	
	@Before("execution(* own.ryze.application.weixin.persistent.dao..*.save*(..)) or execution(* own.ryze.application.weixin.persistent.dao..*.delete*(..))")
	public void setWriteDataSource()
	{
		DataSourceContextHolder.write();
        log.info("数据源切换到：主写库");
	}
	
}
