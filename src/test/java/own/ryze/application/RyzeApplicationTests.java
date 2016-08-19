package own.ryze.application;

import org.junit.Test;

import own.ryze.application.weixin.functioninterface.TailCall;
import static own.ryze.application.weixin.util.StringUtil.*;

public class RyzeApplicationTests
{
	@Test
	public void test() throws Exception
	{
		// String mobile = "13141258019";
		// String mobile1 = "";
		//
		// List<Predicate<String>> list = Arrays.asList(isNotEmpty,isMobile);
		// Predicate<String> reduce = list.stream().reduce(s -> true,(a,b) ->
		// a.and(b));
		// System.out.println(reduce.test(mobile));
		// System.out.println(reduce.test(mobile1));
		//
		// List<String> list = Arrays.asList("13141258019", "2", "23", null);
		// list.stream().map(s -> validate(s, isNotEmpty,
		// isMobile)).forEach(System.out::println);
		//
		// list.stream().filter(s -> validate(s, isNotEmpty,
		// isMobile)).collect(Collectors.toList())
		// .forEach(System.out::println);

		boolean validate2 = validate(null,isMobile,isNotEmpty);
		System.out.println(validate2);
//		long dg1 = dg1(15000);
//		System.out.println(dg1);
//		long dg2 = dg2(15000);
//		System.out.println(dg2);
//		long dg3 = dg3(15000);
//		System.out.println(dg3);
	}

	// 普通递归
	public static long dg1(final long number)
	{
		if (number == 1) return number;
		else return number * dg1(number - 1);
	}

	// 尾递归 但是不晓得为啥java这么写不行，明明是对的。
	public static long dg3(final long number)
	{
		return dg3(number, 1);
	}

	private static long dg3(final long number, final long count)
	{
		if (number == 1) return count;
		return dg3(number - 1, number * count);
	}

	// lambda尾递归，说白了就是上面正常写的用不了才写lambda。
	public static long dg2(final long number)
	{
		return dg(number, 1).invoke();
	}

	private static TailCall<Long> dg(final long number, final long count)
	{
		if (number == 1) return new TailCall<Long>()
		{
			@Override
			public boolean isComplete()
			{
				return true;
			}

			@Override
			public Long result()
			{
				return count;
			}

			@Override
			public TailCall<Long> apply()
			{
				return null;
			}

		};
		return () -> dg(number - 1, number * count);
	}

}
