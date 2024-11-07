package com.sist.service;
import java.util.*;
import com.sist.vo.*;
// Recipe�� ���õ� ����� ��Ƽ� ���� => ���ռ�(������) ���� ���α׷� 
// �����ÿ� �ٸ� Ŭ������ ������ ���� ����� ���α׷� 
// ������ ������ ���� , ���������� �����ϰ� 
public interface RecipeService {
	public RecipeVO recipeMaxHitData();
	public List<RecipeVO> recipeHitTop8();
	public ChefVO chefToday();
	public List<RecipeVO> recipeListData(Map map);
	public int recipeTotalPage();
	public RecipeDetailVO recipeDetailData(int no);
}