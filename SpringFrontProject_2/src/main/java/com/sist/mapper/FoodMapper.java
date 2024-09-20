package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;


public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	// 여러개의 매개변수를 이용할 때 => @Param, Map , VO
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house")
	public int foodTotalPage();
	
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house ORDER BY fno ASC"
			+ "WHERE")
	public List<FoodVO> foodFindData(String address, )
	
}
