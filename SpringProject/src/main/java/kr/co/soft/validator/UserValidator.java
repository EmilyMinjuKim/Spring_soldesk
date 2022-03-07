package kr.co.soft.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.soft.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//유효성 검사 자격이 있니?
		return UserBean.class.isAssignableFrom(clazz);
		//UserBean은 자격이 있어.
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();
		
		//UserBean객체 중 유효성 검사를 적용할 객체만 사용하기 위한 name 추출.
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			
			//유효성 검사 커스터마이징.
			if(userBean.getUser_pw().equals(userBean.getUser_pw2())==false) {
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(beanName.equals("joinUserBean")) {
				if(userBean.isUserIdExist()==false) {
					errors.rejectValue("user_id", "UserIdAlreadyExist");
				}
			}
			
		}
		
		
		
	}

}
