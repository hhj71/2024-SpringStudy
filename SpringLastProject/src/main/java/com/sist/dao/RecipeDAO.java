package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class RecipeDAO {
  @Autowired
  private RecipeMapper mapper;
  
  /*
   *   @Select("SELECT no,title,poster,chef,hit,"
		 +"(SELECT content FROM recipedetail WHERE no=recipe.no) as content "
		 +"FROM recipe "
		 +"WHERE hit=(SELECT MAX(hit) FROM recipe)")
	   public RecipeVO recipeMaxHitData();
	   
	   @Select("SELECT no,title,poster,chef,hit,content,rownum "
			  +"FROM (SELECT no,title,poster,chef,hit,"
			  +"(SELECT content FROM recipedetail WHERE no=recipe.no) as content "
			  +"FROM recipe ORDER BY hit DESC) "
			  +"WHERE rownum<=8")
	   public List<RecipeVO> recipeHitTop8();
   */
  public RecipeVO recipeMaxHitData()
  {
	  return mapper.recipeMaxHitData();
  }
  public List<RecipeVO> recipeHitTop8()
  {
	  return mapper.recipeHitTop8();
  }
  public List<RecipeVO> recipeListData(Map map)
  {
	  return mapper.recipeListData(map);
  }
  public int recipeTotalPage()
  {
	  return mapper.recipeTotalPage();
  }
  public RecipeDetailVO recipeDetailData(int no)
  {
	  mapper.hitIncrement(no);
	  return mapper.recipeDetailData(no);
  }
}