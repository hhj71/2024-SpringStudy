package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper;
   
   /*
    *   @Select("SELECT no,subject,name,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
		 +"FROM spring_replyboard ORDER BY no DESC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
		  public List<ReplyBoardVO> boardListData(@Param("start") int start,
				    @Param("end") int end);
		  @Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id) "
				 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
				 +"#{content},#{pwd},"
				 +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))")
		  public void boardInsert(ReplyBoardVO vo);
		  
		  @Select("SELECT COUNT(*) FROM spring_replyboard")
          public int boardRowCount();
    */
   public List<ReplyBoardVO> boardListData(int start,int end)
   {
	   return mapper.boardListData(start, end);
   }
   public int boardRowCount()
   {
	   return mapper.boardRowCount();
   }
   public void boardInsert(ReplyBoardVO vo)
   {
	   mapper.boardInsert(vo);
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
    *   // ���� 
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
    *     @Select("SELECT group_id,group_step,group_tab "
		 +"FROM spring_replyboard "
		 +"WHERE no=#{no}")
		  public ReplyBoardVO boardGroupData(int no);
		  
		  @Update("UPDATE spring_replyboard SET "
				 +"group_step=group_step+1 "
				 +"WHERE group_id=#{group_id} AND "
				 +"group_step>#{group_step}")
		  public void boardGroupStepIncrement(ReplyBoardVO vo);
		  
		  @Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,"
				 +"group_id,group_step,group_tab,root) "
				 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
				 +"#{content},#{pwd},#{group_id},#{group_step},"
				 +"#{group_tab},#{root})")
		  public void boardReplyInsert(ReplyBoardVO vo);
		  
		  @Update("UPDATE spring_replyboard SET "
				 +"depth=depth+1 "
				 +"WHERE no=#{no}")
		  public void boardDepthIncrement(int no);
    */
   /*
    *                       �亯���� ����   �亯�ȿ� ��� ����
    *                                                 �亯�̹��� ��ġ
    *                       no  group_id  group_step  group_tab  root  depth
    *     AAAAAA             1     1          0          0        0     2
    *       =>KKKKKK         6     1          1          1        1     0
    *       =>BBBBBB         4     1          2          1        1     1
    *        =>CCCCCCC       5     1          3          2        4     0
    *     DDDDDD             2     2          0          0        0     0
    *     EEEEEE             3     3          0          0        0     0
    *     
    */
   // SQL = �ϰ�ó�� 
   // ������ => ī����� , ����Ʈ ���
   // => SQL������ �ٽ� 
   // DML(INSERT,UPDATE,DELETE) �������� ���ÿ� ���� 
   // AOP������� ������� �ִ� 
   // Transaction / Security => AOP�� ���� 
   @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
   // ���� ����� ���ÿ� ������ �ؾ� �� �� 
   public void boardReplyInsert(int pno,ReplyBoardVO vo)
   {
	 // try
	 // {
	   //conn.setAutoCommit(false); // Around
	   ReplyBoardVO pvo=mapper.boardGroupData(pno);
	   mapper.boardGroupStepIncrement(pvo);
	   vo.setGroup_id(pvo.getGroup_id());
	   vo.setGroup_step(pvo.getGroup_step()+1);
	   vo.setGroup_tab(pvo.getGroup_tab()+1);
	   vo.setRoot(pno);
	   mapper.boardReplyInsert(vo);
	   mapper.boardDepthIncrement(pno);
	   //conn.commit(); // 
	  //}catch(Exception ex)
	  //{
		//  conn.rollback() // after-throwing
	  //}
	  //finally
	  //{
		//  conn.setAutoCommit(true); // after
	 // }
   }
   /*
    *    @Select("SELECT pwd FROM spring_replyboard "
		 +"WHERE no=#{no}")
         public String boardGetPassword(int no);
         
         //2.root , depth �б� => ���� ��� 
		  @Select("SELECT root,depth FROM spring_replyboard "
				 +"WHERE no=#{no}")
		  public ReplyBoardVO boardDeleteInfoData(int no);
		  //3.depth==0 ���� 
		  @Delete("DELETE FROM spring_replyboard "
				 +"WHERE no=#{no}")
		  public void boardDelete(int no);
		  
		  //4.depth�� 0�� �ƴϸ� ���� => �����ڰ� ������ �Խù��Դϴ�
		  @Update("UPDATE spring_replyboard SET "
				  +"subject='�����ڰ� ������ �Խù��Դϴ�',"
				  +"content='�����ڰ� ������ �Խù��Դϴ�' "
				  +"WHERE no=#{no}")
		  public void boardSubjectUpdate(int no);
		  //5.�����ÿ� depth�� ���� 
		  @Update("UPDATE spring_replyboard SET "
				 +"depth=depth-1 "
				 +"WHERE no=#{no}")
		  public void boardDepthDecrement(int no);
		  
		  AAAAA  ======> depth 2
		    =>BBBBB  === depth 1
		     =>CCCCC  === depth 0
		    =>DDDD depth 0
		  KKKKK  depth 0
		  FFFFF  depth 1
		   =>JJJJJ depth 0
    */
   @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
   public String boardDelete(int no,String pwd)
   {
	   String result="no";
	   //1.��й�ȣ �˻� 
	   String db_pwd=mapper.boardGetPassword(no);
	   if(db_pwd.equals(pwd))
	   {
		   result="yes";
		   
		   // 2. root,depth�� �о� �´� 
		   ReplyBoardVO vo=mapper.boardDeleteInfoData(no);
		   if(vo.getDepth()==0)// �亯�� ���ٸ� => ����
		   {
			   mapper.boardDelete(no);
		   }
		   else // �亯�� �ִ� ���
		   {
			   mapper.boardSubjectUpdate(no);   
		   }
		   // depth���� 
		   mapper.boardDepthDecrement(vo.getRoot());
	   }
	   return result;
   }
}