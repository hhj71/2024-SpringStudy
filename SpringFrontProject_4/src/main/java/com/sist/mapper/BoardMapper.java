package com.sist.mapper;
// 순서 --> VO => Mapper => DAO => Controller => JSP 전송 
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

// rownum을 사용하면 중간에 끼워넣기가 어렵다
/*
 * 	SELECT : 데이터 검색
 * 		1) 컬럼 대신 사용 - SELECT ~~ (SELECT ~) => 스칼라 서브쿼리
 * 		2) 테이블 대신 사용	- FROM (SELECT ~) => 인라인 뷰 (페이징)
 * 		3) 조건문에서 처리가 가능 - WHERE 컬럼명 (SELECT ~) => 서브쿼리
 * 
 * 	정렬시에는 속도의 최적화 -> INDEX 사용
 * 						   ===== 자동생성 : PK, UK
 * 							| CRUD가 많은 경우 => rebuilding을 해야해서 속도가 저하된다 (게시판, 댓글 등에는 사용 X)
 * 							| 크롤링해서 저장된 경우 => 데이터를 갱신하지 않는다 (INDEX 사용)
 * 
 * param을 사용하거나 map을 사용
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
	// mybatis에서 자동으로 구현해준다 => 리턴형, 매개변수 잘 따져야 한다 => #{} , ${} => 매개변수 o => WHERE 문장이 있는 경우
	// 리턴형 실행 후 - row가 여러개인 경우: LIST ex) List<String>
	// 			   - row 1개, column이 여러개 인 경우: vo  ex)List<BoardVO>
	//			   - row 1개, column 1개 : 일반 데이터형
	
	// 글쓰기
	 @Insert("INSERT INTO vue_board(no, name, subject, content, pwd) "
	 		+ "VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	 public void boardInsert(BoardVO vo);
	
	// 내용보기
	 @Update("UPDATE vue_board SET "
	 		+ "hit=hit+1 "
	 		+ "WHERE no=#{no}")
	 public void hitIncrement(int no);
	 
	 @Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') "
	 		+ "FROM vue_board "
	 		+ "WHERE no=#{no}")
	 public BoardVO boardDetailData(int no);
	 
	 // 삭제 
	  // 비밀번호 검색 
	  @Select("SELECT pwd FROM vue_board "
			 +"WHERE no=#{no}")
	  public String boardGetPassword(int no);
	  // 실제 삭제 
	  @Delete("DELETE FROM vue_board "
			 +"WHERE no=#{no}")
	  public void boardDelete(int no);
	  
	  //수정 => 데이터 읽기
	  @select("SELECT name, ")
	  
	  
	  @Update("UPDATE vue_board SET "
	  		+ "name=#{name}, subject=#{subject}, content=#{content} "
	  		+ "WHERE no=#{no}")
	  public void boardUpdate(BoardVO vo);
}
