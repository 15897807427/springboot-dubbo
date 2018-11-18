package com.xy.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectUtil {

	//生成id
	public static String getId() {
		StringBuffer buf=new StringBuffer();
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmmsss");
		String str=dateformat.format(new Date());
		int s=(int)((Math.random()*9+1)*1000);
		buf.append(str).append(String.valueOf(s));
		return buf.toString();
	}

	//生成日期时间
	public static String  getNowStr(){
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = dateFormat.format(new Date());
		return format;
	}

	//生成日期
	public static String getDateStr(){
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String format = dateFormat.format(new Date());
		return format;
	}

	//获取固定位数随机数
	public static String getRandom(){
		int random=(int)((Math.random()*9+1)*1000);
		return String.valueOf(random);
	}

	//将值填充固定长度
	public static String fillLength(Object obj,int length){
		String objStr=String.valueOf(obj);
		if (StringUtils.equals("null",objStr)||StringUtils.equals(null,objStr)){
			throw new RuntimeException("obj参数传入有误");
		}
		StringBuilder sb=new StringBuilder();
		if (objStr.length()<length){
			int num=length-objStr.length();
			for (int i=0;i<num;i++){
				sb.append(0);
			}
			sb.append(objStr);
			return sb.toString();
		}
		return objStr;
	}

}
