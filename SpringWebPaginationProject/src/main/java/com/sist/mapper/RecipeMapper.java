package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface RecipeMapper {
	   @Select("SELECT no,poster,title,chef,num "
				  +"FROM (SELECT no,poster,title,chef,rownum as num "
				  +"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,poster,title,chef "
				  +"FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail))) "
				  +"WHERE num BETWEEN #{start} AND #{end}")
		   public List<RecipeVO> recipeListData(Map map);
		   
		   @Select("SELECT COUNT(*) FROM recipe "
				  +"WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
		   public int recipeRowCount();
		}
