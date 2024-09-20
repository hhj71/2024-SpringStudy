package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// HandlerMapping => @Controller : DispatcherServlet ���� 
// ��û�� �޾Ƽ� => �����ͺ��̽� ���� => JSP ����� ���� ==> Model
// ��ü ���α׷��� ������ (main)
/*
 *   ��û => ��û �и� (����) 
 *          @RequestMapping ======> POST/GET���ÿ� ó�� => �˻� (PostMapping)
 *                                                       | => <form>
 *                                                     ������ ������ (GetMapping)
 *                                                       <a>
 *                    ================> Spring 6���� ���� 
 *             = @GetMapping
 *             = @PostMapping
 *       -----------------------------------------------------------
 *       => ��û�ϴ� ������ 
 *          ����� ������ ������ 
 *           ��) �󼼺��� => (��ȣ)
 *               �α��� => id,pwd 
 *               �˻� => �˻��� 
 *               ��� => ������ 
 *               ================== getParameter() ===> �Ű�����
 *       => ��û��� ���� : Model (���۰�ü�� �̿�)
 *       ----------------------------------------------------------- 
 *       
 *      JSP => ��û 
 *             === <a> , <form> => .do
 *      Controller 
 *        => @GetMapping(".do")
 *      Mapper => SQL���� 
 *      DAO => SQL���� 
 *      ---------------------
 *      Controller 
 *        => DAO ȣ�� 
 *        => Model�� ��� 
 *      JSP : ��� 
 */
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;
@Controller
// Mapper = DAO = Service = Model => ���ռ��� ���� => ���������� �� 
// Controller ���� 
public class MainController {
  @Autowired
  private RecipeService rService; // DAO ���� ó�� => BI (���õ� ����� ����)
  
  @GetMapping("main/main.do")
  public String main_main(String page,Model model,HttpServletRequest request)
  {
	  if(page==null)
		  page="1";
	  if(page==null)
	    	page="1";
	    int curpage=Integer.parseInt(page);
	    Map map=new HashMap();
	    map.put("start", (curpage*20)-19);
	    map.put("end", curpage*20);
	    List<RecipeVO> list=rService.recipeListData(map);
	    int count=rService.recipeRowCount();
	    int totalpage=(int)(Math.ceil(count/20.0));
	    
	    final int BLOCK=10;
	    int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	    
	    if(endPage>totalpage)
	    	endPage=totalpage;
	    
	    model.addAttribute("list", list);
	    model.addAttribute("count", count);
	    model.addAttribute("curpage", curpage);
	    model.addAttribute("totalpage", totalpage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    
	    // ��Ű ��� 
	    Cookie[] cookies=request.getCookies();
	    List<RecipeVO> cList=new ArrayList<RecipeVO>();
	    // ��Ű ��� List
	    if(cookies!=null)
	    {
	    	// �ֽź��� ��´�
	    	for(int i=cookies.length-1;i>=0;i--)
	    	{
	    		if(cookies[i].getName().startsWith("recipe_"))
	    		{
	    			String no=cookies[i].getValue();
	    			RecipeVO vo=rService.recipeCookieInfoData(Integer.parseInt(no));
	    			cList.add(vo);
	    		}
	    	}
	    }
	    model.addAttribute("cList", cList);
	    model.addAttribute("size", cList.size());
	  // include�� JSP�� ���� 
	    model.addAttribute("main_jsp", "../main/home.jsp");
	  return "main/main";
  }
}