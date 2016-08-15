package own.ryze.application.weixin.config;

import java.util.Properties;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 事务设置
 * @author LCY
 *
 */
@Slf4j
@Configuration
public class TransactionConfig
{
	@Autowired
	private AbstractRoutingDataSource dataSource;

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		log.info("全局事务管理器初始化");
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource);
		return jpaTransactionManager;
	}

	@Bean
	public TransactionInterceptor tInterceptor(PlatformTransactionManager transactionManager)
	{
		log.info("事务拦截器初始化");
		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionManager(transactionManager);
		Properties properties = new Properties();
		properties.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
		properties.setProperty("create*", "PROPAGATION_REQUIRED");
		properties.setProperty("remove*", "PROPAGATION_REQUIRED");
		properties.setProperty("modify*", "PROPAGATION_REQUIRED");
		properties.setProperty("*", "PROPAGATION_REQUIRED");
		ti.setTransactionAttributes(properties);
		return ti;
	}

	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy()
	{
		log.info("事务代理初始化");
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		// 使用jdk接口代理，不使用cglib代理
		transactionAutoProxy.setProxyTargetClass(false);
		transactionAutoProxy.setBeanNames(new String[] { "*ServiceImpl" });
		transactionAutoProxy.setInterceptorNames(new String[] { "tInterceptor" });
		return transactionAutoProxy;
	}
}
