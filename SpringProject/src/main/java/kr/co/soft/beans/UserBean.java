package kr.co.soft.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

	private int user_idx;
	
	@Size(min=2, max=8)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;
	
	@Size(min=4, max=40)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;
	
	@Size(min=4, max=40)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;
	
	@Size(min=4, max=40)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;
	
	private boolean userIdExist;
	private boolean userLogin;
	
	
	public UserBean() {
		this.userIdExist = false;	//최초 회원가입시 중복검사가 안 된 상태이므로 false.
		this.userLogin = false;	//초기값 : 로그인이 안 된 상태.
	}
}
