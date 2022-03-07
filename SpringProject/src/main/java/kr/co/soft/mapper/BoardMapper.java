package kr.co.soft.mapper;

import org.apache.ibatis.annotations.Insert;

import kr.co.soft.beans.ContentBean;

public interface BoardMapper {

	//jdbcType=VARCHAR : MyBatis에서 null값을 허용하도록 
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
			"content_file, content_writer_idx, content_board_idx, content_date) " +
			"values (content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
}
