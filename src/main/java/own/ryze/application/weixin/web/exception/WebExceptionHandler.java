package own.ryze.application.weixin.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import own.ryze.application.weixin.common.PortReturn;
import own.ryze.application.weixin.enums.Return;

/**
 * web异常处理器
 * @author LCY
 *
 */
@ControllerAdvice
public class WebExceptionHandler
{
	/*@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public PortReturn<Object> error()
	{
		return PortReturn.returnJSON(Return.ERROR);
	}*/
}
