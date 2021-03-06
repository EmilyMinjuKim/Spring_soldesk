package kr.co.soft.service;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.soft.beans.ContentBean;
import kr.co.soft.beans.UserBean;
import kr.co.soft.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name="loginUserBean")
	UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		//현재 시간과 오리지널 파일 이름
		String file_name=System.currentTimeMillis()+"_"+
				FilenameUtils.getBaseName(upload_file.getOriginalFilename())+"."+
				FilenameUtils.getExtension(upload_file.getOriginalFilename());
		
		try {
			//경로와 파일이름
			upload_file.transferTo(new File(path_upload+"/"+file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}

	public void addContentInfo(ContentBean writeContentBean) {
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize()>0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println(file_name);
			
			//오라클에 저장될 파일 이름
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		boardDao.addContentInfo(writeContentBean);
	}
}
