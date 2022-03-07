package kr.co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soft.beans.BoardInfoBean;
import kr.co.soft.dao.TopMenuDao;

@Service
public class TopMenuService {

	@Autowired
	private TopMenuDao topMenuDao;
	
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;
	}
	
}

/* mapper에서 쿼리문 실행(mapper->servlet) -> db -> bean -> dao에서 결과값 받음. -> controller -> view
 * 만약 추가적으로 서버단에서 해야 할 작업이 있으면 dao -> service로 결과값 넘겨서 작업한 다음 controller로 넘김.
 * dao는 값을 저장하는 곳(repository) / 받은 값으로 서버에서 별도의 작업하는 곳은 service.
 * */
