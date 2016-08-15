package own.ryze.application.weixin.config.datasource;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 * 
 * @author LCY
 *
 */
public class DataSourceRemote extends AbstractRoutingDataSource
{
	private final int dataSourceNumber;
	// 线程安全Integer
	private AtomicInteger count = new AtomicInteger(0);

	public DataSourceRemote(int dataSourceNumber)
	{
		this.dataSourceNumber = dataSourceNumber;
	}

	@Override
	protected Object determineCurrentLookupKey()
	{
		return DataSourceContextHolder.getDataSourceType();
		// 读 简单负载均衡
//		int number = count.getAndAdd(1);
//		int lookupKey = number % dataSourceNumber;
//		return new Integer(lookupKey);
	}

}
