package own.ryze.application.weixin.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

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
		map.put("code", r.getCode());
		map.put("msg", r.getMsg());
	}
	
	/**
	 * 
	 * @return
	 */
	public static String returnJSON()
	{
		return JSON.toJSONString(map);
	}
	
	/**
	 * 
	 * @param r
	 * @return
	 */
	public static String returnJSON(Return r)
	{
		put(r);
		return returnJSON();
	}
}
