package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.ChefVO;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;


// 결합성을 낮추기 위해 여러 절차를 사용한다

public interface RecipeService {
	public List<RecipeVO> recipeListData(Map map);
	public int recipeRowCount();
	public RecipeDetailVO recipeDetailData(int no);
	public List<ChefVO> chefListData(Map map);
	public int chefTotalPage();
	public List<RecipeVO> chefMakeRecipeData(Map map);
	public int chefMakeRecipeTotalPage(String chef);
	public RecipeVO recipeCookieInfoData(int no);
	public List<RecipeVO> recipeFindData(Map map);
	public int recipeFindTotalPage(Map map);
	public List<FoodVO> foodTop5Data();
	public List<RecipeVO> recipeTop5Data();
}