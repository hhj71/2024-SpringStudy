package com.sist.service;


import java.util.*;
import com.sist.vo.*;

// recipe와 관련된 기능을 모아서 
// 수정시에 다른 클래스에 

public interface RecipeService {
	public RecipeVO recipeMaxHitData();
	public List<RecipeVO> recipeHitTop8();
}
