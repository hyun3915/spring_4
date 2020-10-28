package com.sub.s4.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sub.s4.board.BoardDTO;
import com.sub.s4.util.Pager;

@Controller
@RequestMapping(value = "/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("noticeWrite")
	public ModelAndView setInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("board","notice");
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setInsert(boardDTO);
		String message = "Write Fail!";
		if(result>0) {
			message = "Write Success!";
		}
		
		mv.addObject("msg",message);
		mv.addObject("path", "./noticeList");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	//@RequestMapping(value = "noticeList")
	@GetMapping(value = "noticeList")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = noticeService.getList(pager);
		
		mv.addObject("board", "notice");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		System.out.println("Notice List");
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@GetMapping(value="noticeDelete")
	public ModelAndView setDelete(BoardDTO boardDTO) throws Exception{
		System.out.println("Notice Delete");
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setDelete(boardDTO);
		String message = "Delete Fail!";
		
		if(result>0) {
			message = "Delete Success!";
		}
		mv.setViewName("common/result");
		mv.addObject("msg",message);
		mv.addObject("path", "./noticeList");
		
		return mv;
		
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getOne(BoardDTO boardDTO) throws Exception {
		System.out.println("Notice One");
		ModelAndView mv = new ModelAndView();
		boardDTO = noticeService.getOne(boardDTO);
		if(boardDTO !=null) {
			mv.setViewName("board/boardSelect");
			mv.addObject("dto",boardDTO);
			mv.addObject("board", "notice");
		}else {
			mv.setViewName("common/result");
			mv.addObject("msg", "No Data");
			mv.addObject("path", "./noticeList");
		}
		return mv;
	}
	
	@GetMapping("noticeUpdate")
	public ModelAndView setUpdate2(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardDTO result = noticeService.getOne(boardDTO);
		mv.setViewName("board/boardUpdate");
		mv.addObject("board","notice");
		mv.addObject("dto",result);
		return mv;
	}
	
	@PostMapping("noticeUpdate")
	public ModelAndView setUpdate(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setUpdate(boardDTO);
		String message = "Update Fail!";
		if(result>0) {
			message = "Update Success!";
		}
		
		mv.addObject("msg",message);
		mv.addObject("path", "./noticeList");
		mv.setViewName("common/result");
		
		return mv;
	}


}
