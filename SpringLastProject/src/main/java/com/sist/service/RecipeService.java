package com.sist.service;


import java.util.*;
import com.sist.vo.*;

// recipe�� ���õ� ����� ��Ƽ� 
// �����ÿ� �ٸ� Ŭ������ 

public interface RecipeService {
	public RecipeVO recipeMaxHitData();
	public List<RecipeVO> recipeHitTop8();
}
