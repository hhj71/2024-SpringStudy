package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeDetailVO;

@Controller
public class RecipeController {
	@Autowired	
	private RecipeDAO rDao;
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no, Model model)
	{
		// DB¿¬µ¿
		RecipeDetailVO vo = rDao.recipeDetailData(no);
		String dataString=vo.getData();
		
		
	//
		
	}
}
