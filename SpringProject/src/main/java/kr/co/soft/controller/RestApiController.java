package kr.co.soft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.soft.service.UserService;

@RestController	//응답받을 값이 데이터이므로 RestController 사용.
public class RestApiController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean chk = userService.checkUserIdExist(user_id);
		
		return chk+" ";
		
		//공백을 붙여준 이유는 boolean값인 chk를 string으로 형변환 해주기 위해.
		//RestController는 view resolver를 거치지 않음. 즉, 서버를 거치지 않음.
		//곧장 jsp로 가는데, 전달된 값을 받는 것은 jsp에서 선언된 ajax.
	}
	
	
}
