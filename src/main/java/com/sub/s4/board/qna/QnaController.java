package com.sub.s4.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sub.s4.board.BoardDTO;
import com.sub.s4.util.Pager;

@Controller
@RequestMapping(value = "/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping(value = "qnaList")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = qnaService.getList(pager);
		
		BoardDTO boardDTO = ar.get(0);
		QnaDTO qnaDTO = (QnaDTO)boardDTO;
		System.out.println(qnaDTO.getDepth());
		
		mv.addObject("board", "qna");
		mv.addObject("list",ar);
		mv.addObject("pager", pager);
		System.out.println("Qna List");
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", "qna");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView setInsert(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setInsert(boardDTO);
		String message = "Write Fail!";
		if(result>0) {
			message = "Write Success!";
		}
		
		mv.addObject("msg",message);
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView getOne(BoardDTO boardDTO) throws Exception {
		System.out.println("QnA One");
		ModelAndView mv = new ModelAndView();
		boardDTO = qnaService.getOne(boardDTO);
		if(boardDTO !=null) {
			mv.setViewName("board/boardSelect");
			mv.addObject("dto",boardDTO);
			mv.addObject("board", "qna");
		}else {
			mv.setViewName("common/result");
			mv.addObject("msg", "No Data");
			mv.addObject("path", "./boardList");
		}
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView setReply() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardReply");
		mv.addObject("board", "qna");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView setReply(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setReply(boardDTO);
		
		String message = "Reply Write Fail";
		
		if(result>0) {
			message = "Reply Write Success";
		}
		
		mv.addObject("msg", message);
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate2(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardDTO result = qnaService.getOne(boardDTO);
		mv.setViewName("board/boardUpdate");
		mv.addObject("dto",boardDTO);
		mv.addObject("board","qna");
		mv.addObject("dto",result);
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public ModelAndView setUpdate(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setUpdate(boardDTO);
		String message = "Update Fail!";
		if(result>0) {
			message = "Update Success!";
		}
		
		mv.addObject("msg",message);
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping(value="qnaDelete")
	public ModelAndView setDelete(BoardDTO boardDTO) throws Exception{
		System.out.println("Qna Delete");
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setDelete(boardDTO);
		String message = "Delete Fail!";
		
		if(result>0) {
			message = "Delete Success!";
		}
		mv.setViewName("common/result");
		mv.addObject("msg",message);
		mv.addObject("path", "./qnaList");
		
		return mv;
		
	}

}
