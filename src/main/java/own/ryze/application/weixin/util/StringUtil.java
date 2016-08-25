package own.ryze.application.weixin.util;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 字符串工具
 * 
 * @author LCY
 *
 */
public class StringUtil
{
	private StringUtil()
	{
	}

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
	 * 首字母大写
	 */
	public static Function<String, String> toUpperCaseFirstOne = str -> (new StringBuilder())
			.append(Character.toUpperCase(str.charAt(0))).append(str.toLowerCase().substring(1)).toString();

	/**
	 * 多条件校验字符串 (先写先校验)
	 * 
	 * @param str
	 *            校验字符串
	 * @param predicate
	 *            校验条件
	 * @return
	 */
	@SafeVarargs
	public static boolean validate(final String str, final Predicate<String>... predicates)
	{
		return Stream.of(predicates).reduce((current,next) -> current.and(next)).orElseGet(StringUtil::identity).test(str);
	}
	
	/**
	 * 默认校验失败
	 * @return
	 */
	private static Predicate<String> identity()
	{
		return s -> false;
	}
	
	/**
	 * 多操作修改字符串(先写先操作)
	 * 
	 * @param str
	 *            字符串
	 * @param functions
	 *            操作
	 * @return
	 */
	@SafeVarargs
	public static String operate(final String str, final Function<String, String>... functions)
	{
		return Stream.of(functions).reduce((current,next) -> current.andThen(next)).orElseGet(Function::identity).apply(str);
	}
}
