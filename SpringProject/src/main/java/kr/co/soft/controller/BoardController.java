package kr.co.soft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soft.beans.ContentBean;
import kr.co.soft.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx, Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/main";
	}
	
	@GetMapping("read")
	private String read() {
		
		return "board/read";
	}
	
	@GetMapping("write")
	private String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean) {
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
							BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
	}
	
	@GetMapping("modify")
	private String modify() {
		
		return "board/modify";
	}
	
	@GetMapping("delete")
	private String delete() {
		
		return "board/delete";
	}
}
