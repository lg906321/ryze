package own.ryze.application.weixin.config.datasource;


public class DataSourceContextHolder
{
	private static ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

	public static ThreadLocal<DataSourceType> getHolder()
	{
		return holder;
	}
	
	public static DataSourceType getDataSourceType()
	{
		return holder.get();
	}

	public static void read()
	{
		holder.set(DataSourceType.read);
	}

	public static void write()
	{
		holder.set(DataSourceType.write);
	}
}
