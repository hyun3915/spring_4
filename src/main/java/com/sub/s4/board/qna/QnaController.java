package com.sub.s4.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sub.s4.board.BoardDTO;
import com.sub.s4.board.file.BoardFileDTO;
import com.sub.s4.util.Pager;

@Controller
@RequestMapping(value = "/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@PostMapping("summernoteDelete")
	public ModelAndView summernoteDelete(String file, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean result = qnaService.summernoteDelete(file, session);
		mv.addObject("msg", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	@PostMapping("summernote")
	public ModelAndView summernote(MultipartFile file,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String fileName = qnaService.summernote(file, session);
		System.out.println(fileName);
		
		String name = session.getServletContext().getContextPath()+File.separator;
		name = name+"resources"+File.separator+"upload"+File.separator;
		name = name+"qna"+File.separator+fileName;
		System.out.println(name);
		
		mv.addObject("msg", fileName);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(BoardFileDTO boardFileDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", "qna");
		mv.addObject("fileDTO",boardFileDTO);
		mv.setViewName("fileDown");
		return mv;
	}
	
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
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView ex1() {
		ModelAndView mv = new ModelAndView();
		System.out.println("Null Pointer");
		mv.setViewName("error/error/back");
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
	public ModelAndView setInsert(BoardDTO boardDTO, MultipartFile [] files, HttpSession session) throws Exception{
		
		for(int i=0; i<files.length;i++) {
			System.out.println(files[i].getOriginalFilename());
		}
		
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setInsert(boardDTO, files, session);
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
