package com.sub.s4.board.notice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sub.s4.MyTestCase;
import com.sub.s4.board.BoardDTO;
import com.sub.s4.util.Pager;

public class NoticeServiceTest extends MyTestCase{

	@Autowired
	private NoticeService noticeService;
	
	@Test
	public void getListTest() throws Exception{
		
		Pager pager = new Pager();
		
		
		List<BoardDTO> ar = noticeService.getList(pager);
		assertEquals(10, ar.size());
	}

}
