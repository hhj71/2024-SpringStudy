package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class BoardDAO {
	// ���������κ��� ������ Mapper�� �ּҸ� ���� => ��û
	@Autowired // �ּҰ� �ڵ� ���� => @Autowired => ��ü���� ������ �ȴ�
	// ������ => Ŭ������ �����ֱ� ���� -> �ַ� ��ü �ּ� ����
	// @Autowired => 1) �������, 2) ������ , 3) setXxx() ���� ��� / �޼ҵ� �ȿ����� ���Ұ�
	// annotation�� �ؿ� �ִ� ����/�޼ҵ�/������ ����, ���� �ִ� ������� ����
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(int start, int end)
	{
		return mapper.boardListData(start, end);
	}
	public int boardTotalPage()
	{
		return mapper.boardTotalPage(); 
	}
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	public BoardVO boardDetailData(int no)
	   {
		   mapper.hitIncrement(no);
		   return mapper.boardDetailData(no);
	   }
	   /*
	    *   // ��й�ȣ �˻� 
			  @Select("SELECT pwd FROM vue_board "
					 +"WHERE no=#{no}")
			  public String boardGetPassword(int no);
			  // ���� ���� 
			  @Delete("DELETE FROM vue_board "
					 +"WHERE no=#{no}")
			  public void boardDelete(int no);
	    */
	   public String boardDelete(int no,String pwd)
	   {
		   String result="no";
		   String db_pwd=mapper.boardGetPassword(no);
		   if(db_pwd.equals(pwd))
		   {
			   result="yes";
			   mapper.boardDelete(no);
		   }
		   return result;
	   }
	   // ���� 
	   /*
	    *   @Select("SELECT name,subject,content "
			 +"FROM vue_board "
			 +"WHERE no=#{no}")
	        public BoardVO boardUpdateData(int no);
	    */
	   public BoardVO boardUpdateData(int no)
	   {
		   return mapper.boardUpdateData(no);
	   }
	   /*
	    *   @Update("UPDATE vue_board SET "
			 +"name=#{name},subject=#{subject},content=#{content} "
			 +"WHERE no=#{no}")
	        public void boardUpdate(BoardVO vo);
	    */
	   public String boardUpdate(BoardVO vo)
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
}
