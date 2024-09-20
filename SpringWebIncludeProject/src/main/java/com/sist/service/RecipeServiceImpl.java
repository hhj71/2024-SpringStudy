package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *    JSP = Controller = Service = Repository = ����Ŭ 
 *                     =         =            =
 */
/*
 * 		1. mapper => SQL ���� �ۼ�
 * 		2. DAO ���� ����
 * 		3. service�� ��� ~Service
 * 		4. service ���� 	~ServiceImpl
 * 		===> ����/���� ���� -> DAO�� �߰��ϸ� ������ ����� ������ => ���� ����
 * 		===> ���ռ��� ���� ���α׷� ==> �ٸ� Ŭ������ ������ ���� ���
 */
@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private RecipeDAO dao;

	@Override
	public List<RecipeVO> recipeListData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeListData(map);
	}
	
	@Override
	public int recipeRowCount() {
		// TODO Auto-generated method stub
		return dao.recipeRowCount();
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		// TODO Auto-generated method stub
		return dao.recipeDetailData(no);
	}

	@Override
	public List<ChefVO> chefListData(Map map) {
		// TODO Auto-generated method stub
		return dao.chefListData(map);
	}

	@Override
	public int chefTotalPage() {
		// TODO Auto-generated method stub
		return dao.chefTotalPage();
	}

	@Override
	public List<RecipeVO> chefMakeRecipeData(Map map) {
		// TODO Auto-generated method stub
		return dao.chefMakeRecipeData(map);
	}

	@Override
	public int chefMakeRecipeTotalPage(String chef) {
		// TODO Auto-generated method stub
		return dao.chefMakeRecipeTotalPage(chef);
	}

	@Override
	public RecipeVO recipeCookieInfoData(int no) {
		// TODO Auto-generated method stub
		return dao.recipeCookieInfoData(no);
	}

	@Override
	public List<RecipeVO> recipeFindData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeFindData(map);
	}

	@Override
	public int recipeFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeFindTotalPage(map);
	}

	@Override
	public List<FoodHouseVO> foodTop5Data() {
		// TODO Auto-generated method stub
		return dao.foodTop5Data();
	}

	@Override
	public List<RecipeVO> recipeTop5Data() {
		// TODO Auto-generated method stub
		return dao.recipeTop5Data();
	}
	
}