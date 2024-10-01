package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class BoardDAO {
	// 스프링으로부터 구현된 Mapper의 주소를 대입 => 요청
	@Autowired // 주소값 자동 주입 => @Autowired => 객체에만 적용이 된다
	// 스프링 => 클래스의 생명주기 관리 -> 주로 객체 주소 주입
	// @Autowired => 1) 멤버변수, 2) 생성자 , 3) setXxx() 에서 사용 / 메소드 안에서는 사용불가
	// annotation은 밑에 있는 변수/메소드/생성자 적용, 옆에 있는 내용들을 제어
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
	    *   // 비밀번호 검색 
			  @Select("SELECT pwd FROM vue_board "
					 +"WHERE no=#{no}")
			  public String boardGetPassword(int no);
			  // 실제 삭제 
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
	   // 수정 
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
