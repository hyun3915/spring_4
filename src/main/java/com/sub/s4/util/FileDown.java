package com.sub.s4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.sub.s4.board.file.BoardFileDTO;

public class FileDown extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String board = (String)model.get("board");
		BoardFileDTO boardFileDTO = (BoardFileDTO)model.get("fileDTO");
		System.out.println(boardFileDTO.getFileName());
		String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+board);
		
		File file = new File(path, boardFileDTO.getFileName());
			
		//파일 한글처리
		response.setCharacterEncoding("UTF-8");
		
		//파일의 크기
		response.setContentLength((int)file.length());
		
		//다운로드시 파일이름 인코딩
		String downName = URLEncoder.encode(boardFileDTO.getOriName(),"UTF-8");
		
		//header 설정
		response.setHeader("Content-Disposition", "attachment;fileName\""+downName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//Client로 전송
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		
		
		FileCopyUtils.copy(fi, os);
		
		os.close();
		fi.close();
		
	}

}
