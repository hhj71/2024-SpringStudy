package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
  @Autowired
  private FoodMapper mapper;
  
  /*
   *   @Select("SELECT fno,name,poster,address,rownum "
		 +"FROM (SELECT fno,poster,address "
		 +"FROM project_food_house ORDER BY hit DESC) "
		 +"WHERE rownum<=5")
        public List<FoodVO> foodHitTop5();
   */
  public List<FoodVO> foodHitTop5()
  {
	  return mapper.foodHitTop5();
  }
  /*
   *   // ��� ***
	  @Select("SELECT fno,name,poster,phone,type,num "
			 +"FROM (SELECT fno,name,poster,phone,type,rownum as num "
			 +"FROM (SELECT fno,name,poster,phone,type "
			 +"FROM project_food_house ORDER BY fno ASC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
	  // ��������
	  @Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house")
	  public int foodTotalPage();
   */
  public List<FoodVO> foodListData(int start,int end)
  {
	  return mapper.foodListData(start, end);
  }
  public int foodTotalPage()
  {
	  return mapper.foodTotalPage();
  }
  /*
   *   @Update("UPDATE project_food_house SET "
		 +"hit=hit+1 "
		 +"WHERE fno=#{fno}")
	  public void hitIncrement(int fno);
	  @Select("SELECT * FROM project_food_house "
			 +"WHERE fno=#{fno}")
	  public FoodVO foodDetailData(int fno);
   */
  public FoodVO foodDetailData(int fno)
  {
	  mapper.hitIncrement(fno);
	  return mapper.foodDetailData(fno);
  }
  public FoodVO foodInfoData(int fno)
  {
	  return mapper.foodDetailData(fno);
  }
  public List<FoodVO> foodFindListData(Map map)
  {
	  return mapper.foodFindListData(map);
  }
  public int foodFindTotalPage(Map map)
  {
	  return mapper.foodFindTotalPage(map);
  }
}