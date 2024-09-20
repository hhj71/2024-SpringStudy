package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *     JSP  ==> RecipeController
 *     ����Ŭ ==> RecipeDAO
 */
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
// JSP�� ���� ���� ===> DispatcherServlet�� ����
public class RecipeController {
   @Autowired
   private RecipeDAO dao;
   
   // ��Ī => ����� ���� URL => �޼ҵ� ȣ��
   @RequestMapping("recipe/list.do")
   /*
    *    int page=null (X)
    *    String page=null
    *    vue/react => int page
    */
   public String recipe_list(String page,Model model)
   {
      if(page==null)
         page="1";
      int curpage=Integer.parseInt(page);
      Map map=new HashMap();
      map.put("start", (curpage*20)-19);
      map.put("end", curpage*20);
      List<RecipeVO> list=dao.recipeListData(map);
      int count=dao.recipeRowCount();
      int totalpage=(int)(Math.ceil(count/20.0));
      
      final int BLOCK=10;
      int startPage=((curpage-1)/BLOCK*BLOCK)+1;
      int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
      
      if(endPage>totalpage)
         endPage=totalpage;
      
      model.addAttribute("list",list);
      model.addAttribute("count",count);
      model.addAttribute("curpage",curpage);
      model.addAttribute("totalpage",totalpage);
      model.addAttribute("startPage",startPage);
      model.addAttribute("endPage",endPage);
      
      return "recipe/list";
   }
   
   @GetMapping("recipe/detail.do")
   public String recipe_detail(int no, Model model)
   {
	   // �����ͺ��̽� ���� => �󼼺���
	   
	   
   }
}
