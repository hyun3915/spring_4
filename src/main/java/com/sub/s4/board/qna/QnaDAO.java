package com.sub.s4.board.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sub.s4.board.BoardDAO;
import com.sub.s4.board.BoardDTO;
import com.sub.s4.util.Pager;

@Repository
public class QnaDAO implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="com.sub.s4.board.qna.QnaDAO.";
	
	public int setReply(BoardDTO boardDTO) throws Exception{
		return sqlSession.insert(namespace+"setReply", boardDTO);
	}
	
	public int setReplyUpdate(BoardDTO boardDTO) throws Exception{
		return sqlSession.update(namespace+"setReplyUpdate", boardDTO);
	}	
	
	@Override
	public int setInsert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+"setInsert", boardDTO);
	}

	@Override
	public int setUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+"setUpdate", boardDTO);
	}
	

	@Override
	public int setDelete(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace+"setDelete", boardDTO);
	}

	@Override
	public List<BoardDTO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"getList",pager);
	}

	@Override
	public BoardDTO getOne(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getOne", boardDTO);
	}

	@Override
	public long getCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getCount", pager);
	}

}
