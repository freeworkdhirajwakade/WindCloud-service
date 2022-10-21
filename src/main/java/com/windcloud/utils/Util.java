package com.windcloud.utils;

import com.windcloud.entity.User;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class Util {
	
	public static User mergeUsers(User first, User second){
        Class<?> clas = first.getClass();       
        Field[] fields1 = clas.getDeclaredFields();
        System.out.println("fields1 size="+fields1.length);
        try {
            for (Field field : fields1) 
            {
            	field.setAccessible(true);
            	if(!"serialVersionUID".equals(field.getName()))
            	{
            		Object value1 = field.get(first);
            		if (!Collection.class.isAssignableFrom(field.getType())) 
            		{
            			if(value1!=null)
                        {
                        	field.setAccessible(true);          	   
                            field.set(second, value1);
                        }
            		}
                    
            	}
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return second;
    }
	
	@SuppressWarnings("unused")
	private String encodeValue(String value) throws UnsupportedEncodingException {
	    return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
	}
	
	@SuppressWarnings("unused")
	private String decode(String value) throws UnsupportedEncodingException {
	    return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
	}

}
