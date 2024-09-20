package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.BoardVO;

//SQL문장을 이용해서 =>CRUD를 수행 => 마이바티스에 의해 자동구현
public interface BoardMapper {
	 @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			 +"FROM (SELECT no,subject,name,regdate,hit "
			 +"FROM spring_board ORDER BY no DESC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<BoardVO> boardListData(Map map);
	 
	 @Select("SELECT CEIL(COUNT(*)/10.0 FROM spring_board")
	 public int boardTotalPage();
	 
}
