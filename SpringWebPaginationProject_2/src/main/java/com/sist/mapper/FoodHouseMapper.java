package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodHouseVO;

public interface FoodHouseMapper {
	@Select("SELECT fno, poster, name, num "
			+"FROM (SELECT fno, poster, name, rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(project_food_house FH_FNO_PK)*/fno, poster, name "
			+"FROM project_food_house)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodHouseVO> FoodHouseListData(Map map);
	
	@Select("SELECT COUNT(*) FROM project_food_house ")
	public int FoodHouseRowCount();
}
