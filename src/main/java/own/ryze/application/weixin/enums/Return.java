package own.ryze.application.weixin.enums;

/**
 * 
 * @author LCY
 *
 */
public enum Return
{
	SUCCESS(0, "SUCCESS"),
	FAIL(1,"FAIL");
	
	private int code;
	private String msg;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	private Return(){}

	private Return(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
}
