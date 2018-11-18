package com.xy.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 返回页面对象
 * @author xuyuan
 *
 */
public class ResponseVO {
	
	/**
	 * 成功
	 * @return
	 */
	public static Map<String,Object> success(Object data){
		Map<String,Object> map = new HashMap<>();
		map.put("code", "0000");
		map.put("msg", "成功");
		map.put("data", data);
		return map;
	}
	
	
	/**
	 * 异常
	 * @param msg
	 * @return
	 */
	public static Map<String,Object> exception(String msg){
		Map<String,Object> map = new HashMap<>();
		map.put("code", "9999");
		map.put("msg", msg);
		return map;
	}

}
