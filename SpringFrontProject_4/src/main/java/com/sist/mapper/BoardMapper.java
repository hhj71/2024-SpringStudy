package com.sist.mapper;
// ���� --> VO => Mapper => DAO => Controller => JSP ���� 
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

// rownum�� ����ϸ� �߰��� �����ֱⰡ ��ƴ�
/*
 * 	SELECT : ������ �˻�
 * 		1) �÷� ��� ��� - SELECT ~~ (SELECT ~) => ��Į�� ��������
 * 		2) ���̺� ��� ���	- FROM (SELECT ~) => �ζ��� �� (����¡)
 * 		3) ���ǹ����� ó���� ���� - WHERE �÷��� (SELECT ~) => ��������
 * 
 * 	���Ľÿ��� �ӵ��� ����ȭ -> INDEX ���
 * 						   ===== �ڵ����� : PK, UK
 * 							| CRUD�� ���� ��� => rebuilding�� �ؾ��ؼ� �ӵ��� ���ϵȴ� (�Խ���, ��� ��� ��� X)
 * 							| ũ�Ѹ��ؼ� ����� ��� => �����͸� �������� �ʴ´� (INDEX ���)
 * 
 * param�� ����ϰų� map�� ���
 *  
 */
public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM vue_board ORDER BY no DESC))"
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT COUNT(*) FROM vue_board")
	public int boardTotalPage();
	// mybatis���� �ڵ����� �������ش� => ������, �Ű����� �� ������ �Ѵ� => #{} , ${} => �Ű����� o => WHERE ������ �ִ� ���
	// ������ ���� �� - row�� �������� ���: LIST ex) List<String>
	// 			   - row 1��, column�� ������ �� ���: vo  ex)List<BoardVO>
	//			   - row 1��, column 1�� : �Ϲ� ��������
	
	// �۾���
	 @Insert("INSERT INTO vue_board(no, name, subject, content, pwd) "
	 		+ "VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	 public void boardInsert(BoardVO vo);
	
	// ���뺸��
	 @Update("UPDATE vue_board SET "
	 		+ "hit=hit+1 "
	 		+ "WHERE no=#{no}")
	 public void hitIncrement(int no);
	 
	 @Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') "
	 		+ "FROM vue_board "
	 		+ "WHERE no=#{no}")
	 public BoardVO boardDetailData(int no);
	 
	 // ���� 
	  // ��й�ȣ �˻� 
	  @Select("SELECT pwd FROM vue_board "
			 +"WHERE no=#{no}")
	  public String boardGetPassword(int no);
	  // ���� ���� 
	  @Delete("DELETE FROM vue_board "
			 +"WHERE no=#{no}")
	  public void boardDelete(int no);
	  
	  //���� => ������ �б�
	  @select("SELECT name, ")
	  
	  
	  @Update("UPDATE vue_board SET "
	  		+ "name=#{name}, subject=#{subject}, content=#{content} "
	  		+ "WHERE no=#{no}")
	  public void boardUpdate(BoardVO vo);
}
