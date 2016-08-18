package own.ryze.application.weixin.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 字符串工具
 * 
 * @author LCY
 *
 */
public class StringUtil
{
	private StringUtil(){}
	
	public static Predicate<String> isEmpty = s -> s == null || "".equals(s) || "null".equals(s);

	public static Predicate<String> isNotEmpty = s -> s != null && !"".equals(s) && !"null".equals(s);
	

	public static Predicate<String> isMobile = s -> s.matches(
			"^(1[3,5,8,7]{1}[\\d]{9})|(((400)-(\\d{3})-(\\d{4}))|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$");

	public static Predicate<String> isEmail = s -> s.matches(
			"^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");

	public static Predicate<String> isURL = s -> s.matches(
			"^(https|http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$");

	/**
	 * 是否身份证
	 */
	public static Predicate<String> isID = s -> s.matches(
			"(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$)");

	/**
	 * 条件
	 */
	private static Predicate<String> predicate = (s) -> true;

	/**
	 * 多条件校验字符串
	 * 
	 * @param str
	 *            校验字段
	 * @param predicate
	 *            校验条件
	 * @return
	 */
	@SafeVarargs
	public static boolean validate(final String str, final Predicate<String>... predicates)
	{
		List<Predicate<String>> list = Arrays.asList(predicates);
		list.forEach(p -> predicate = and(predicate, p));
		return predicate.test(str);
	}
	
	/**
	 * 校验条件拼接
	 * 
	 * @param main
	 *            主条件
	 * @param support
	 *            辅条件
	 * @return
	 */
	private static Predicate<String> and(final Predicate<String> main, final Predicate<String> support)
	{
		return main.and(support);
	}

}
