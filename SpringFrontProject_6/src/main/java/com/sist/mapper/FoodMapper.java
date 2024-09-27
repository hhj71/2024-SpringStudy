package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT CEIL((COUNT(*)/20.0) FROM project_food_house "
			+ "WHERE type LIKE '%'||type||'%'")
	public int foodTypeTotalPage(String type);
	
	
	
	
	
	
	@Select("SELECT fno, name, poster, rownum "
			+ "FROM (SELECT fno, name, poster"
			+ " "
			
			)
	
	
	
	
}
