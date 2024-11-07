package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
  @Select("SELECT no,subject,name,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
		 +"FROM spring_replyboard ORDER BY group_id DESC , group_step ASC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  public List<ReplyBoardVO> boardListData(@Param("start") int start,
		    @Param("end") int end);
  @Select("SELECT COUNT(*) FROM spring_replyboard")
  public int boardRowCount();
  
  @Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id) "
		 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
		 +"#{content},#{pwd},"
		 +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))")
  public void boardInsert(ReplyBoardVO vo);
  
  // �󼼺��� 
  @Update("UPDATE spring_replyboard SET "
		 +"hit = hit+1 "
		 +"WHERE no=#{no}")
  public void hitIncrement(int no);
  
  @Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
		 +"FROM spring_replyboard "
		 +"WHERE no=#{no}")
  public ReplyBoardVO boardDetailData(int no);
  // ���� 
  @Select("SELECT pwd FROM spring_replyboard "
		 +"WHERE no=#{no}")
  public String boardGetPassword(int no);
  
  @Update("UPDATE spring_replyboard SET "
		 +"name=#{name},subject=#{subject},content=#{content} "
		 +"WHERE no=#{no}")
  public void boardUpdate(ReplyBoardVO vo);
  // ���� 
  //1.��й�ȣ Ȯ�� => �̹� ���� 
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
  // �亯�ϱ� 
  @Select("SELECT group_id,group_step,group_tab "
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
  // �˻� = �������� 
  
}