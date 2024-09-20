package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
  // ��� = ������������  
  @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit "
		 +"FROM spring_board ORDER BY no DESC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  public List<BoardVO> boardListData(@Param("start") int start,@Param("end") int end);
  /*
   *   <select id="boardListData" resultType="BoardVO" parameterType="">
   *               �޼ҵ�                ������             �Ű����� 
   *     SELECT~
   *   </select>
   */
  // boardList(1,10) => ������ ���� => @Param => �Ű������� ������ ����� ���� 
  // Map => 1�� �̿�ÿ��� , VO
  @Select("SELECT COUNT(*) FROM spring_board")
  public int boardRowCount();
  // �߰� 
  @Insert("INSERT INTO spring_board(no,name,subject,content,pwd) "
		 +"VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
  public void boardInsert(BoardVO vo);
  // �󼼺���
  @Update("UPDATE spring_board SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}")
  public void hitIncrement(int no);
  
  @Select("SELECT no,name,subject,content,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,hit "
		 +"FROM spring_board "
		 +"WHERE no=#{no}")
  public BoardVO boardDetailData(int no);
  // ���� 
  
  @Select("SELECT pwd FROM spring_board "
		 +"WHERE no=#{no}")
  public String boardGetPassword(int no); // ��й�ȣ ������ ���� 
  
  @Update("UPDATE spring_board SET "
		 +"name=#{name} , subject=#{subject},content=#{content}" 
		 +"WHERE no=#{no}")
  public void boardUpdate(BoardVO vo);
  // ���� 
  @Delete("DELETE FROM spring_board "
		 +"WHERE no=#{no}")
  public void boardDelete(int no);
  // �˻� 
}