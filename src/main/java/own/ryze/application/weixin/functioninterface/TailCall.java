package own.ryze.application.weixin.functioninterface;

import java.util.stream.Stream;

/**
 * 递归
 * @author LCY
 *
 * @param <T>
 */
@FunctionalInterface
public interface TailCall<T>
{
	TailCall<T> apply();

	default boolean isComplete()
	{
		return false;
	}

	default T result()
	{
		return null;
	}

	default T invoke()
	{
		//创建无限集合 完成状态过滤
		return Stream.iterate(this, TailCall::apply).filter(TailCall::isComplete).findFirst().get().result();
	}

}
