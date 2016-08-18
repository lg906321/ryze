package own.ryze.application.weixin.common;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import own.ryze.application.weixin.enums.Return;

/**
 * 接口返回值
 * 
 * @author LCY
 * @param <T>
 *
 */
@Data
public class PortReturn<T> implements Serializable
{
	private static final long serialVersionUID = -5849564424292078824L;

	@ApiModelProperty("接口返回码")
	public final int code;
	@ApiModelProperty("接口返回码描述")
	public final String msg;
	@ApiModelProperty("接口返回数据")
	public final T data;

	public static <T> PortReturn<T> success(final T data)
	{
		Return success = Return.SUCCESS;
		return returnJSON(success.code, success.msg,data);
	}

	public static <T> PortReturn<T> fail()
	{
		Return fail = Return.FAIL;
		return returnJSON(fail.code, fail.msg);
	}

	public static <T> PortReturn<T> error()
	{
		Return error = Return.ERROR;
		return returnJSON(error.code, error.msg);
	}

	public static <T> PortReturn<T> returnJSON(final int code, final String msg, final T data)
	{
		return new PortReturn<T>(code, msg, data);
	}

	public static <T> PortReturn<T> returnJSON(final int code, final String msg)
	{
		return returnJSON(code, msg,null);
	}

}
