package own.ryze.application.weixin.config.datasource;

import lombok.Getter;

/**
 * 数据源类型
 * @author LCY
 *
 */
@Getter
public enum DataSourceType
{
	write("write", "主库"), read("read", "从库");
	private String type;
	private String name;

	private DataSourceType(String type, String name)
	{
		this.type = type;
		this.name = name;
	}
}
