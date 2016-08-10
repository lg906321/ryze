package own.ryze.application.weixin.util;

/**
 * 字符串工具
 * @author LCY
 *
 */
public class StringUtil
{
	/**
	 * 空串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		return (str == null || "".equals(str) || "null".equals(str));
	}
	
	/**
	 * 非空串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		return (str != null || !"".equals(str) || !"null".equals(str));
	}
}
