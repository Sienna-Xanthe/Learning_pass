package cn.race.common.response;


/**
 * 异常编码
 */
public enum CommonErrorCode implements ErrorCode {
	/////////////////////////////////////////////////
	NAME_NULL(10000,"名字为空"),
	PASSWORD_NULL(10001,"密码为空"),


	PHONE_NULL(10002,"手机号为空或格式不正确"),
	PHONE_EXIST(10003,"手机号已存在"),
	ROLR_ERROR(10004,"角色不匹配"),
	USER_NOTEXIST(10005,"用户或密码不存在"),
	ROLE_NULL(10006,"角色为空"),
	PASSWORD_NO(10007,"密码不一致"),

	/////////////////////////////////////////////////

	UNKNOWN(999999,"未知错误");



	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private CommonErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	public static CommonErrorCode setErrorCode(int code) {
       for (CommonErrorCode errorCode : CommonErrorCode.values()) {
           if (errorCode.getCode()==code) {
               return errorCode;
           }
       }
	       return null;
	}
}
