package own.ryze.application;

import static own.ryze.application.weixin.util.StringUtil.isMobile;
import static own.ryze.application.weixin.util.StringUtil.isNotEmpty;
import static own.ryze.application.weixin.util.StringUtil.validate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import own.ryze.application.weixin.functioninterface.TailCall;
import own.ryze.application.weixin.util.StringUtil;

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
//		List<String> list = Arrays.asList("13141258019", "2", "23", null);
//		list.stream().map(s -> validate(s, isNotEmpty, isMobile)).forEach(System.out::println);
//
//		list.stream().filter(s -> validate(s, isNotEmpty, isMobile)).collect(Collectors.toList())
//				.forEach(System.out::println);

		boolean validate2 = StringUtil.validate("13141258019",  StringUtil.isMobile,StringUtil.isEmpty);
		System.out.println(validate2);
	}

}
