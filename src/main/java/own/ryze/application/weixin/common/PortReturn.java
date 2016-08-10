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
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849564424292078824L;

	@ApiModelProperty("接口返回码")
	private int code;
	@ApiModelProperty("接口返回码描述")
	private String msg;
	@ApiModelProperty("接口返回数据")
	private T data;

	public static <T> PortReturn<T> returnJSON(T data, Return r)
	{
		PortReturn<T> portReturn = new PortReturn<T>();

		portReturn.setCode(r.getCode());
		portReturn.setMsg(r.getMsg());
		portReturn.setData(data);

		return portReturn;
	}

	public static <T> PortReturn<T> returnJSON(Return r)
	{
		return returnJSON(null, r);
	}

	public static <T> PortReturn<T> returnJSON(int code, String msg)
	{
		Return custom = Return.CUSTOM;
		custom.setCode(code);
		custom.setMsg(msg);

		return returnJSON(custom);
	}

}
