package com.windcloud.utils;

import com.windcloud.entity.User;
import java.lang.reflect.Field;
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

}
