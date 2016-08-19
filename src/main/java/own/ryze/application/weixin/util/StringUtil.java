package own.ryze.application.weixin.util;

import java.util.function.Function;
import java.util.function.Predicate;

import own.ryze.application.weixin.functioninterface.TailCall;

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
			.append(Character.toUpperCase(str.charAt(0))).append(str.toUpperCase().substring(1)).toString();

	/**
	 * 多条件校验字符串
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
		return recursionPredicate(predicates.length - 1, predicates).invoke().test(str);
	}
	
	/**
	 * 多操作修改字符串
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
		return recursionFunction(functions.length - 1, functions).invoke().apply(str);
	}

	/**
	 * 递归创建条件
	 * 
	 * @param length
	 * @param predicates
	 * @return
	 */
	@SafeVarargs
	private static TailCall<Predicate<String>> recursionPredicate(final int length, final Predicate<String>... predicates)
	{
		return length == 0 ? finish(predicates[0]) : () -> recursionPredicate(length - 1, predicates[length].and(predicates[length - 1]));
	}
	
	/**
	 * 递归创建操作
	 * @param length
	 * @param predicates
	 * @return
	 */
	@SafeVarargs
	private static TailCall<Function<String,String>> recursionFunction(final int length, final Function<String,String>... functions)
	{
		return length == 0 ? finish(functions[0]) : () -> recursionFunction(length - 1, functions[length].andThen(functions[length - 1]));
	}

	/**
	 * 回调返回值
	 * 
	 * @param value
	 * @return
	 */
	private static <T> TailCall<T> finish(final T value)
	{
		return new TailCall<T>()
		{
			@Override
			public boolean isComplete()
			{
				return true;
			}

			@Override
			public T result()
			{
				return value;
			}

			@Override
			public TailCall<T> apply()
			{
				return null;
			}
		};
	}
}
