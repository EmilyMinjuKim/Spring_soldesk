package kr.co.soft.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentBean {

	private int content_idx;
	
	@NotBlank	//NotNull과 같음
	private String content_subject;
	
	@NotBlank
	private String content_text;
	
	//실제 파일 내용을 저장
	private MultipartFile upload_file;
	
	//파일명
	private String content_file;
	private int content_writer_idx;
	private int content_board_idx;
	private String content_date;
}
