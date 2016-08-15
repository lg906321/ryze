package own.ryze.application;

import org.junit.Test;

import own.ryze.application.weixin.config.datasource.DataSourceType;

public class RyzeApplicationTests
{

	@Test
	public void test() throws Exception
	{
		DataSourceType read = DataSourceType.read;
		DataSourceType read1 = DataSourceType.read;
		boolean equals = read.equals(read1);
		System.out.println(equals);
	}

}
