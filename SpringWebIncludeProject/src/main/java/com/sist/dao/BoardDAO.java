package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<ReplyBoardVO> boardListData(int start, int end)
	{
		return mapper.boardListData(start, end);
	}
	public int boardRowCount()
	{
		return mapper.boardRowCount();
	}
	
	public void boardInsert(ReplyBoardVO vo)
	{
		
	}
	 public ReplyBoardVO boardDetailData(int no)
	   {
		   mapper.hitIncrement(no);
		   return mapper.boardDetailData(no);
	   }
	 /*
	    *   @Update("UPDATE spring_replyboard SET "
			 +"hit = hit+1 "
			 +"WHERE no=#{no}")
			  public void hitIncrement(int no);
			  
			  @Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
					 +"FROM spring_replyboard "
					 +"WHERE no=#{no}")
			  public ReplyBoardVO boardDetailData(int no);
	    */
	   public ReplyBoardVO boardDetailData(int no)
	   {
		   mapper.hitIncrement(no);
		   return mapper.boardDetailData(no);
	   }
	   
	   public ReplyBoardVO boardUpdateData(int no)
	   {
		   return mapper.boardDetailData(no);
	   }
	   /*
	    *   // 수정 
			  @Select("SELECT pwd FROM spring_replyboard "
					 +"WHERE no=#{no}")
			  public String boardGetPassword(int no);
			  
			  @Update("UPDATE spring_replyboard SET "
					 +"name=#{name},subject=#{subject},content=#{content} "
					 +"WHERE no=#{no}")
			  public void boardUpdate(ReplyBoardVO vo);
	    */
	   public String boardUpdate(ReplyBoardVO vo)
	   {
		   String result="no";
		   String db_pwd=mapper.boardGetPassword(vo.getNo());
		   if(db_pwd.equals(vo.getPwd()))
		   {
			   result="yes";
			   mapper.boardUpdate(vo);
		   }
		   return result;
	   }
	   
	   /*
	    * 
	    * 
	    */
	   public void boardReplyInsert(int pno, ReplyBoardVO vo )
	   {
		   
	   }
	   
	   @Transactional(propagation = Propagation.)
	   // 여러 기능이 동시에 동작을 해야 할 때 
	}
