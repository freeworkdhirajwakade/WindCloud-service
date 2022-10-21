package com.windcloud.constants;

public class CommanConstants 
{
	private CommanConstants() {}
	//repsonse status
	public final static Integer SUCCESS=1;
	public final static Integer FAILED=0;
	//User Status
	public final static String CRAETED="Created";
	public final static String APPROVED="Approved";
	
	//Response Message
	public final static String MSG_SUCCESS_SAVE="Successfully Saved";
	public final static String MSG_FAIED="Failed...Try again";
	public final static String EMAIL_EMPTY="Email Id should Not be Empty";
	public final static String USER_ALREADY_EXIST="User Already Exist";
	public static final String LOGIN_SUCCESS = "Successfully Login";
	public static final String MAIL_FROM = "dhirajwakade@gmail.com";
	public static final String MAIL_SUBJECT = "WindAndCloud Register OTP";
	public static final String OTP_SEND_SUCCESS = "OTP send successfully";
	public static final String INVALID_TOKEN = "Invalid Token";
	public static final String FORGOT_PASS_MSG = "Link has been sent on email. Please check mail,";
	public static final String EMAIL_ID_NOT_EXIST = "EMail Id is not Exist";
	public static final String SUCCESS_FORGOTTED = "Successfully Forgotted  Password";
}
