package own.ryze.application.weixin.enums;

/**
 * 
 * @author LCY
 *
 */
public enum Return
{
	SUCCESS(0, "SUCCESS"),
	ERROR(9999,"ERROR"),
	FAIL(1,"FAIL");
	
	public final int code;
	public final String msg;
	
	Return(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
}
