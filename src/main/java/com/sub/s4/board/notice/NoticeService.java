package com.sub.s4.board.notice;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sub.s4.board.BoardDTO;
import com.sub.s4.board.BoardService;
import com.sub.s4.board.file.BoardFileDTO;
import com.sub.s4.member.memberFile.MemberFileDTO;
import com.sub.s4.util.FileSaver;
import com.sub.s4.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileSaver filesaver;

	@Override
	public int setInsert(BoardDTO boardDTO, MultipartFile[] files, HttpSession session) throws Exception {
		//파일을 하드디스크에 저장
		String path = session.getServletContext().getRealPath("/resources/upload/notice");
		File file = new File(path);
		System.out.println(path);
		
		//Notice Insert
		int result = noticeDAO.setInsert(boardDTO);
		
		//NoticeFile Insert		
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()!=0) {
				
			String fileName = filesaver.saveCopy(file, multipartFile);
			System.out.println(fileName);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			noticeDAO.setInsertFile(boardFileDTO);
			
			}
		}
		
//		String fileName ="";
//		
//		int result = noticeDAO.setInsert(boardDTO);
//		
//		if(files.length != 0) {
//			fileName = filesaver.saveCopy(file, files);
//			
//			MemberFileDTO memberFileDTO = new MemberFileDTO();
//			memberFileDTO.setId();
//			
//		}
		
		return 0;
	}

	@Override
	public int setUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setUpdate(boardDTO);
	}

	@Override
	public int setDelete(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setDelete(boardDTO);
	}

	@Override
	public List<BoardDTO> getList(Pager pager) throws Exception {
		
		pager.makeRow();
		pager.setTotalCount(noticeDAO.getCount(pager));
		pager.makePage();
		
		return noticeDAO.getList(pager);
	}

	@Override
	public BoardDTO getOne(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getOne(boardDTO);
	}

}
