package com.windcloud.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import com.windcloud.entity.User;

public class Util {
	
	public static User mergeUsers(User first, User second){
        Class<?> clas = first.getClass();       
        Field[] fields1 = clas.getDeclaredFields();
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
	public static String encodeValue(String value) throws UnsupportedEncodingException {
		Base64.Encoder encoder = Base64.getEncoder();  
		return encoder.encodeToString(value.getBytes());  
	   
	}
	
	@SuppressWarnings("unused")
	public static String decode(String value) throws UnsupportedEncodingException {
		 Base64.Decoder decoder = Base64.getDecoder();  
	    return new String(decoder.decode(value));    
	}
	
	public static LocalDateTime nextMinute(Integer min) {
		return LocalDateTime.now().plusMinutes(min);
	}
	public static String generateSixDigitOTP() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d",number);
	}
	public static String generateFourDigitOTP() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(9999);
	    return String.format("%04d",number);
	}
	
	public static String generateTransactionRef()
	{
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static Long currentUnixTimeStamp()
	{
		Long unixTime = Instant.now().getEpochSecond();
		return unixTime;
	}
	
	public static int generateRandomNo()
	{
		int min=1,max=10;
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public static int[] generateRank() {
		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			int n = generateRandomNo();
			arr[i] = setElementInArr(arr, n);
		}
		return arr;
	}
	
	public static int setElementInArr(int[] arr, int n)
	{
		if(isDuplicateElement(arr,n))
		{
			return setElementInArr(arr,generateRandomNo());
		}
		else
		{
			return n;
		}
	}
	
	public static boolean isDuplicateElement(int[] arr,int n)
	{
		boolean flg=false;
		for(int j=0;j<arr.length;j++)
		{
			if(arr[j]==n)
			{
				flg=true;
				break;
			}
		}
		return flg;				
	}
}
