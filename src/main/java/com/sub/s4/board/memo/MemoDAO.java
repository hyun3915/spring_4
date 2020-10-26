package com.sub.s4.board.memo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sub.s4.board.BoardDAO;
import com.sub.s4.board.BoardDTO;
import com.sub.s4.util.Pager;

@Repository
public class MemoDAO{
	
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="com.sub.s4.board.memo.MemoDAO.";
	
	public MemoDTO getOne(MemoDTO memoDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getOne", memoDTO);
	}

	public int setInsert(MemoDTO memoDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+"setInsert", memoDTO);
	}


	public List<MemoDTO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"getList", pager);
	}
	
	public long getCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getCount", pager);
	}
	
	public int setDelete(MemoDTO memoDTO) throws Exception{
		return sqlSession.delete(namespace+"setDelete", memoDTO);
	}


}
