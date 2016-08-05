package own.ryze.application.weixin.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import own.ryze.application.weixin.constant.WebConstant;
import own.ryze.application.weixin.enums.Return;

/**
 * 接口返回值
 * @author LCY
 *
 */
public class PortReturn
{
	private static Map<String,Object> map = Collections.synchronizedMap(new HashMap<String,Object>());
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value)
	{
		map.put(key, value);
	}
	
	/**
	 * 
	 * @param r
	 */
	public static void put(Return r)
	{
		map.put(WebConstant.CODE, r.getCode());
		map.put(WebConstant.MSG, r.getMsg());
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String,Object> returnJSON()
	{
		return map;
	}
	
	/**
	 * 
	 * @param r
	 * @return
	 */
	public static Map<String,Object> returnJSON(Return r)
	{
		put(r);
		return returnJSON();
	}
}
