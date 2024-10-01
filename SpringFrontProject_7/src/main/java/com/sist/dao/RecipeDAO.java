package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;

import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
/*
 *	@Select("SELECT no, title, poster, chef, content, num "
			 +"FROM (SELECT no, title, poster, chef, content, rownum as num "
			 +"FROM (SELECT no, title, poster, chef, content "
			 +"FROM recipe ORDER BY no ASC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<RecipeVO> recipeListData(@Param("start") int start,@Param("end") int end);
	  // 총페이지
	  @Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	  public int recipeTotalPage(); 
 *    // 상세보기 => Cookie
		  @Update("UPDATE recipe SET "
				 +"hit=hit+1 "
				 +"WHERE no=#{no}")
		  public void hitIncrement(int no);
		  @Select("SELECT * FROM recipe "
				 +"WHERE no=#{no}")
		  public RecipeVO recipeDetailData(int no);
 */
	public List<RecipeVO> recipeListData(int start, int end)
	{
		return mapper.recipeListData(start, end);
	}
	public int foodTotalPage()
	   {
		   return mapper.recipeTotalPage();
	   }
	public RecipeVO recipeDetailData(int no)
	   {
		   mapper.hitIncrement(no);
		   return mapper.recipeDetailData(no);
	   }
}
