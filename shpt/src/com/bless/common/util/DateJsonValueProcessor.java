package com.bless.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 
 * @author wang
 *	把JSON中的date类型，装换为java.util.date类型
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	 	private String format = "yyyy-MM-dd";   
	    private SimpleDateFormat sdf = new SimpleDateFormat(format);   
	 
	    //处理数组的值
	    @Override
	    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
	        return this.process(value);
	    }
	 
	    //处理对象的值
	    @Override
	    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
	        return this.process(value);
	    }
	     
	    //处理Date类型返回的Json数值
	    private Object process(Object value) {
	        if (value == null) {
	            return "";
	        } else if (value instanceof Date)
	            return sdf.format((Date) value);
	        else {
	            return value.toString();
	        }
	    }
}
