package kr.co.soft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soft.beans.ContentBean;
import kr.co.soft.mapper.BoardMapper;

@Repository // DAO 저장소에는 @Repository 어노테이션을 붙여야 한다.
public class BoardDao {

	@Autowired
	private BoardMapper boardMapper;

	public void addContentInfo(ContentBean writeContetnBean) {
		boardMapper.addContentInfo(writeContetnBean);
	}

}
