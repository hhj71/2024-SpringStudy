package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FoodVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

public interface FoodHouseMapper {
	@Select("SELECT fno, poster, name, num "
			+"FROM (SELECT fno, poster, name, rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(project_food_house FH_FNO_PK)*/fno, poster, name "
			+"FROM project_food_house)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> FoodHouseListData(Map map);
	
	@Select("SELECT COUNT(*) FROM project_food_house ")
	public int FoodHouseRowCount();
	
	// 상세보기
	   @Update("UPDATE project_food_house SET "
			  +"hit=hit+1 "
			  +"WHERE fno=#{fno}")
	   public void FoodHouseHitIncrement(int fno);
	   
	   @Select("SELECT * FROM project_food_house "
			  +"WHERE fno=#{fno}")
	   public FoodVO FoodHouseDetailData(int fno);
	   
	// 쿠키 정보 데이터 
	   @Select("SELECT fno, name, poster "
			   +"FROM project_food_house "
			   +"WHERE fno=#{fno}")
	   public FoodVO FoodHouseCookieInfoData(int fno);
}
