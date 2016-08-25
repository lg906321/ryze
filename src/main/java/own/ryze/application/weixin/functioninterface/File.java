package own.ryze.application.weixin.functioninterface;

/**
 * 文件处理
 * @author LCY
 *
 * @param <T> 处理类型 wirte read
 * @param <R> 返回类型 boolean int
 * @param <X> 抛出异常类型
 */
public interface File<T,R,X extends Throwable>
{
	R accept(T t)throws X;
}
