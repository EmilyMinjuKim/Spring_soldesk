package kr.co.soft.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soft.beans.UserBean;
import kr.co.soft.dao.UserDao;
import kr.co.soft.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	public boolean checkUserIdExist(String user_id) {
		String user_name = userDao.checkUserIdExist(user_id);
		if(user_name==null) 
			return true;	//중복검사 통과 
		else 
			return false;
	}
	
	//받아오는 값도 없고 따로 수행할 작업이 없는 경우, dao거치지 않고 service에서 mapper호출해서 실행.
	public void addUserInfo(UserBean joinUserBean) {
		userMapper.addUserInfo(joinUserBean);
	}
	
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2!=null) {
			loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
			loginUserBean.setUserLogin(true);	//로그인 된 상태
		}
	}
	
	public void getModifyUserInfo(UserBean modifyUserInfo) {
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_idx());
		
		modifyUserInfo.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserInfo.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserInfo.setUser_idx(loginUserBean.getUser_idx());
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		//현재 로그인한 유저의 idx번호.
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		userDao.modifyUserInfo(modifyUserBean);
	}
	
}
