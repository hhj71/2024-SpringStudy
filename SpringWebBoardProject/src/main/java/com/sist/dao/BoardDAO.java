package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 *   1. ~DAO : �����ͺ��̽� ����
 *   2. ~Service : DAO�������� �Ѱ��� ���� , ���ռ��� ���� ���α׷� 
 *   3. ~Manager : Open API
 *   4. ~Controller : Model 
 *   5. ~RestController : �ڹٽ�ũ��Ʈ , ��Ʋ�� , �÷��� 
 *      =============== JSON
 *   6. ~ControllerAdvice : ���� ����ó�� 
 *   ==================================== ���������� ���� (���� ~ �Ҹ�)
 *      ~VO : ����� �������� 
 *      ~Mapper : ������ �� ���� (�������̽�)
 *   ==================================== ������ ���� 
 */
@Repository //DAO�޸� �Ҵ� ��û => ���� => �̱������� ���� 
public class BoardDAO {
   // �ʿ��� ��ü�� ���������κ��� ��� �´� => ����(DI) => �ڵ� ���� : @Autowried
   @Autowired
   private BoardMapper mapper; // ������ Ŭ������ �ּҰ��� ���� 
   // �޼ҵ�ȿ����� ������̼��� ����� �� ���� 
   /*
    *    ===> ������̼� �Ʒ� , ������̼� ���� �ִ� ���� ���� 
    *    1. class��  => TYPE (Ŭ���� ������)
    *    2. �޼ҵ� �� => METHOD (�޼ҵ� ������)
    *    3. ������� �� => FIELD (������� ������)
    *    4. �Ű����� �� => PARAMETER (�Ű����� ������)
    *    5. �������� => CONTSTRUCTOR (������ ������)
    */
   /*
    *    @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit "
		 +"FROM spring_board ORDER BY no DESC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
         public List<BoardVO> boardListData(@Param("start") int start,@Param("end") int end);
    */
   public List<BoardVO> boardListData(int start,int end)
   {
	   return mapper.boardListData(start, end);
   }
   /*
    *   @Insert("INSERT INTO spring_board(no,name,subject,content,pwd) "
		 +"VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
        public void boardInsert(BoardVO vo);
    */
   public void boardInsert(BoardVO vo)
   {
	   mapper.boardInsert(vo); // commit�� ����  session.close()
   }
   
   /*
    *    @Select("SELECT COUNT(*) FROM spring_board")
         public int boardRowCount();
    */
   public int boardRowCount()
   {
	   return mapper.boardRowCount();
   }
   
   /*
    *   @Update("UPDATE spring_board SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}")
		  public void hitIncrement(int no);
		  
		  @Select("SELECT no,name,subject,content,"
				 +"TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,hit "
				 +"FROM spring_board "
				 +"WHERE no=#{no}")
		  public BoardVO boardDetailData(int no);
    */
   public BoardVO boardDetailData(int no)
   {
	   mapper.hitIncrement(no);
	   return mapper.boardDetailData(no);
   }
   
   public BoardVO boardUpdateData(int no)
   {
	   return mapper.boardDetailData(no);
   }
   
   /*
    *    @Select("SELECT pwd FROM spring_board "
		 +"WHERE no=#{no}")
		  public String boardGetPassword(int no); // ��й�ȣ ������ ���� 
		  
		  @Update("UPDATE spring_board SET "
				 +"name=#{name} , subject=#{subject},content=#{content}" 
				 +"WHERE no=#{no}")
		  public void boardUpdate(BoardVO vo);
    */
   public boolean boardUpdate(BoardVO vo)
   {
	   boolean bCheck=false;
	   String db_pwd=mapper.boardGetPassword(vo.getNo());
	   if(db_pwd.equals(vo.getPwd()))
	   {
		   bCheck=true;
		   // ���� 
		   mapper.boardUpdate(vo);
	   }
	   return bCheck;
   }
   /*
    *   @Select("SELECT pwd FROM spring_board "
		 +"WHERE no=#{no}")
		  public String boardGetPassword(int no);
    *   @Delete("DELETE FROM spring_board "
		 +"WHERE no=#{no}")
        public void boardDelete(int no);
    */
   // ����� ���� �Ű������� �����Ӱ� ����� ���� 
   public boolean boardDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   String db_pwd=mapper.boardGetPassword(no);
	   if(pwd.equals(db_pwd))
	   {
		   bCheck=true;
		   mapper.boardDelete(no);
	   }
	   return bCheck;
   }
}



