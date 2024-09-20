package com.sist.dao;

import com.sist.vo.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

/*
 * public interface FoodHouseMapper {
	@Select("SELECT fno, poster, title, num "
			+"FROM (SELECT fno, poster, title, rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(project_food_house FH_FNO_PK) fno, poster, title "
			+"FROM project_food_house)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
			public List<FoodHouseVO> FoodHouseListData(Map map);
		}

 */
@Repository
public class FoodHouseDAO {
	 @Autowired
	  private FoodHouseMapper mapper;
	 
	  public List<FoodHouseVO> FoodHouseListData(Map map)
	   {
		   return mapper.FoodHouseListData(map);
	   }
	  public int FoodHouseRowCount()
	  {
		 return mapper.FoodHouseRowCount();
	  }
}
