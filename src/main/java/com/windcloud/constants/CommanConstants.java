package com.windcloud.constants;

public class CommanConstants 
{
	private CommanConstants() {}
	//repsonse status
	public final static Integer SUCCESS=1;
	public final static Integer FAILED=0;
	//User Status
	public final static String USER_STATUS_CRAETED="Created";
	public final static String USER_STATUS_APPROVED="Approved";
	
	//Transaction Status
	public static final String TRX_STATUS_APPROVED = "Approved";
	public static final String TRX_STATUS_CREATED = "Created";
	
	
	//Scheduler time
	
	//5 min scheduler
	public static final long GAME_CREATE_TIME_MIN = (long) (3*60*1000);
	public static final long GAME_GENERATE_RESULT_TIME_MIN = (long) (2.5*60*1000);
	public static final long GAME_FETCH_TIME_MIN = (long) (2*60*1000);
	public static final long GAME_CREATE_INITIAL_TIME_MIN = (long) (1000);
	
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
	public static final String FORGOT_PASS_MSG = "Link has been sent on email. Please check Email,";
	public static final String EMAIL_ID_NOT_EXIST = "Email Id is not Exist";
	public static final String SUCCESS_FORGOTTED = "Successfully Forgoted  Password";
	public static final String INVALID_URL = "Invalid URL";
	public static final String FORGOT_PASS_LINK_EXPIRED = "Forgot Password Link has Expired";
	public static final String ENTER_VALID_USERNAME_PASS = "Please Enter Valid Username and Password";
	public static final String USER_DETAILS_EMPTY = "User Details Should Not Be Empty";
	public static final String CREATOR_EMPTY = "Creator Should Not Be Empty";
	public static final String TRANSAC_TYPE_SHOULD_NOT_EMPTY = "Transaction Type Should not be Empty";
	public static final String CREATOR_INVALID = "Invalid Creator";
	
	public static final String MSG_SUCCESS_UPDATE = "Successfully Updated";
	public static final String INVALID_TRANSAC_REF = "Invalid Transaction reference No";
	public static final String TRANSACTION_APPROVEED = "Transactino Approved Successfully";
	public static final String INEFFICIENT_AMOUNT = "Infficient Amount in your account";
	public static final String SUCCESS_DATA_FOUND = "Successfully Data found";
	public static final String ROOM_NO_GENERATOR = "RM";
	public static final String ROOM_NAME_EMPTY = "Room name Should not be empty";
	public static final String INVALID_ROOM_NO = "Invalid Room No";
	public static final String INVALID_USER_NO = "Invalid User No";
	public static final String BET_TYPE_EMPTY = "Bet Type Should not be empty";
	public static final String ROOM_NO_EMPTY = "Room should not be empty";
	public static final String CAR_NO_EMPTY = "Car number should not be empty";
	public static final String BET_AMOUNT_EMPTY = "Bet Amount Should Not empty or Zero";
	public static final String INVALID_BET_DETAILS = "Invalid Bet Details Id";
	public static final String DATA_NOT_FOUND = "Data Not Found";
	public static final String GAME_NAME_EMPTY = "Game Name Should Not Empty";
	public static final String BETTING_LIMIT_EMPTY = "Betting Limit Should Not be Empty";
	public static final String CLOSE_TIME_EMPTY = "Close Time should not be Empty";
	public static final String OPEN_TIME_EMPTY = "Open Time should not be Empty";
	public static final String GAME_STATUS_ACTIVE ="Active";
	public static final Boolean GAME_SCH_STATUS_ACTIVE = true;
	public static final String GAME_NO_GENERATOR = "GM";
	public static final String GAME_STATUS_CLOSED = "Closed";
	public static final String BETS_EMPTY = "Bets should not be empty";
	public static final String GAME_DETAILS_EMPTY = "Game Details should not be empty";
	public static final String ROOM_DEAILS_EMPTY = "Room Details Not found";
	public static final String GAME_CLOSED = "Game is ready closed";
	public static final String BET_STATUS_CREATED = "Created";
	public static final String INVALID_RANK_ID = "Invalid Rank Id";
	public static final String BET_STATUS_WIN = "Win";
	public static final String BET_STATUS_LOSS = "Loss";
	public static final String BET_STATUS_COMPLETED = "Completed";
	
}
