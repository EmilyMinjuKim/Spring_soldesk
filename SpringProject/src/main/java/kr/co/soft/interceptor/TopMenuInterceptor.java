package kr.co.soft.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soft.beans.BoardInfoBean;
import kr.co.soft.beans.UserBean;
import kr.co.soft.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {
	
	private TopMenuService topMenuService;
	private UserBean loginUserBean;
	
	//HandlerInterceptor에서는 @Autowired를 사용할 수 없으므로 직접 객체를 생성해야 한다.
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}

	
}
