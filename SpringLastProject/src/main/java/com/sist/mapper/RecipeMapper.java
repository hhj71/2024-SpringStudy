package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface RecipeMapper {
   // 1. ����Ŭ���� ���࿩�� Ȯ�� 
   @Select("SELECT no,title,poster,chef,hit,"
		 +"(SELECT content FROM recipedetail WHERE no=recipe.no) as content "
		 +"FROM recipe "
		 +"WHERE hit=(SELECT MAX(hit) FROM recipe)")
   public RecipeVO recipeMaxHitData();
   
   @Select("SELECT r.no, r.title, r.poster, r.chef, r.hit, rd.content " +
           "FROM recipe r " +
           "JOIN recipedetail rd ON r.no = rd.no " +
           "WHERE r.hit != (SELECT MAX(hit) FROM recipe) " +
           "ORDER BY r.hit DESC " +
           "FETCH FIRST 8 ROWS ONLY")
   public List<RecipeVO> recipeHitTop8();
   
   // ��� ���
   @Select ("SELECT no, poster, title, chef, hit, num "
   		+ "FROM(SELECT no, poster, title, chef, hit, rownum as num "
   		+ "FROM(SELECT /*+INDEX_ASC(recipe recipe_no_pk)*/no, poster, title, chef, hit "
   		+ "FROM recipe WHERE no IN(SELECT no FROM recipe "
   		+ "INTERSECT SELECT no FROM recipedetail)))"
   		+ "WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map map);
   
   @Select("SELECT CEIL (COUNT(*)/12.0) FROM recipe "
   		+ "WHERE no IN(SELECT no FROM recipe "
   		+ "INTERSECT SELECT no FROM recipedetail)")
   	public int recipeTotalPage();
   
   // �󼼺���
   @Update("UPDATE recipe SET "
   		+  "hit=hit+1 "
   		+ "WHERE no = #{no}")
   public void hitIncrement(int no);
   
   @Select("SELECT * FROM recipedetail " // column���� �� ������ ������ * ��밡��
   		+ "WHERE no=#{no}")
   public RecipeDetailVO recipeDetailData(int no);
}