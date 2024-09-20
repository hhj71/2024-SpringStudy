package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.service.*;
import com.sist.vo.*;
// Service(X) => Repository 
// main/main.do
@Controller
/*
 *   "1.�Ŵ��� ������ �ķ���ũ�� �ְ� �߰ſ� ���� �־��. ���� ��� ������ �����ؿ�.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/63b1c1893de0b820694a874018f6a9f91.jpg
      2.�ҿ� �⸧�� �θ��� ���ĸ� ���ƿ�. �������� , �����뽺Ǭ^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/98c771b5a1bd92d27134366a28b4957e1.jpg
      3.�� ���� �ö���� �ް��� �ְ� �����.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/e296a5122d5b9e37d3b36ae52532e98d1.jpg
      4.�ް� ���ڰ� �ͱ� �����ϸ� ��� �Ŵ����� �ξ� ���ҿ��� ���ƿ�. �����밡�� �Ŵ����� ������ �߰� ���� �־��.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/e1219e68ac6b0b3a44c7f87df0ae9bc01.jpg
"
 */
public class RecipeController {
   @Autowired
   private RecipeService rService;
   /*@Autowired
   public RecipeController(RecipeService rService)
   {
	   this.rService=rService;
   }*/
   @GetMapping("recipe/detail_before.do")
   public String recipe_detail_before(int no, HttpServletResponse response, RedirectAttributes ra)
   {
	   // Cookie ���� => ���� => ������ ����
	   // ��Ű�� ������ HttpServletResponse response�� �Ű������� �޾ƿ;� �Ѵ�. 
	   // ���� ��ü => Model : forward
	   // ���� => RedirectAttributes : sendRedirect
	   Cookie cookie = new Cookie("recipe_"+no, String.valueOf(no));
	   // ��Ű�� ���� ��ġ�� ������ , ���ڿ��� ������ ���� 
	   //							Ű	   ,	 ��
	   //						  getName()	    getValue()
	   cookie.setMaxAge(60*60*24); // �ʴ��� ���� => ���� �Ⱓ
	   cookie.setPath("/"); // ���� ��ġ
	   // �������� ����
	   response.addCookie(cookie);
	   ra.addAttribute("no", no);
	   // ra.addFlashAttribute("no", no); ==> �����͸� ����� �� �� ���
	   return "redirect:../recipe/detail.do";
   }
   @GetMapping("recipe/detail.do")
   // recipe/detail.do?no=
   public String recipe_detail(int no,Model model)
   {
	   // �����ͺ��̽� ���� => �����͸� �б� 
	   RecipeDetailVO vo=rService.recipeDetailData(no);
	   String data=vo.getData();
	   data=data.replace("����", "");
	   vo.setData(data.trim());
	   // detail.jsp�� ����� ������ �����ش� 
	   model.addAttribute("vo", vo);
	   
	   List<String> mList=new ArrayList<String>();
	   List<String> iList=new ArrayList<String>();
	   
	   String[] make=vo.getFoodmake().split("\n");
	   for(String m:make)
	   {
		   StringTokenizer st=new StringTokenizer(m,"^");
		   mList.add(st.nextToken());
		   iList.add(st.nextToken());
	   }
	   model.addAttribute("mList", mList); // ������ ���
	   model.addAttribute("iList", iList); // �̹���
	   
	   
	   // Model�� JSP�� ���۽ÿ� ��� : forward
	   model.addAttribute("main_jsp", "../recipe/detail.jsp");
	   return "main/main";
   }
   @GetMapping("recipe/chef_list.do")
   // �ݺ��ڵ� => �޼ҵ�ó�� , AOP�̿�
   //                     ====== �ڵ� ȣ���� ���� 
   /*
    *      ȣ�� ��ġ => ����(JoinPoint)
    *      public void display()
    *      {
    *          ===== ȣ�� (before)
    *          try
    *          {
    *               ===== �ٽ� ��� 
    *          }catch(Exception ex)
    *          {
    *               ===== after-throwing
    *          }
    *          finally
    *          {
    *               ===== after
    *          }
    *          return   ====== after-returning    
    *      }
    */
   //           ===== �ݺ����� => �ݵ�� ȣ���ؼ� ����Ѵ� 
   public String recipe_chef_list(String page,Model model)
   {
	   
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=50;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   // �����ͺ��̽��� ��� 
	   List<ChefVO> list=rService.chefListData(map);
	   int totalpage=rService.chefTotalPage();
	   // �������� �б�
	   // ������ ���� 
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("main_jsp", "../recipe/chef_list.jsp");
	   
	   return "main/main";
   }
   // ���� �� 
   @GetMapping("recipe/chef_detail.do")
   public String recipe_chef_detail(String page,String chef,Model model)
   {
	   // chef�� ���� recipe��� 
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   // List
	   Map map=new HashMap();
	   map.put("chef", chef);
	   map.put("start", start);
	   map.put("end", end);
	   List<RecipeVO> list=rService.chefMakeRecipeData(map);
	   // �������� 
	   int totalpage=rService.chefMakeRecipeTotalPage(chef);
	   // ���� 
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("chef", chef);
	   model.addAttribute("main_jsp", "../recipe/chef_detail.jsp");
	   return "main/main";
   }
   @GetMapping("recipe/cookie_all.do")
   public String recipe_cookie_all(HttpServletRequest request, Model model)
   {
	// ��Ű ���
	    Cookie[] cookies=request.getCookies();
	    List<RecipeVO> cList = new ArrayList<RecipeVO>();
	    
	    if(cookies!=null)
	    {
	    	//�ֽź��� ��´�
	    	for(int i=cookies.length-1; i>=0; i--)
	    	{
	    		if(cookies[i].getName().startsWith("recipe_"))
	    		{
	    			String no = cookies[i].getValue();
	    			RecipeVO vo = rService.recipeCookieInfoData(Integer.parseInt(no));
	    			cList.add(vo);
	    		}
	    	}
	    }
	   model.addAttribute("cList",cList);
	   model.addAttribute("size", cList.size());
	   model.addAttribute("main_jsp", "../recipe/cookie_all.jsp");
	   return "main/main";
   }
   @GetMapping("recipe/cookie_delete.do")
   public String recipe_cookie_delete(HttpServletRequest request, HttpServletResponse response)
   {
	   Cookie[] cookies= request.getCookies();
	   if(cookies!=null)
	   {
		   for(int i=cookies.length-1; i>=0; i--)
	    	{
	    		if(cookies[i].getName().startsWith("recipe_"))
	    		{
	    			 cookies[i].setPath("/");
					   cookies[i].setMaxAge(0);// ��Ű ���� 
					   response.addCookie(cookies[i]); // �������� �˸� 
				   }
	    	}	
	   }
	   return "redirect:../main/main.do";
   }
   // recipe/find.do => URL�̶�� => ���ǹ� : ������̼� ==> if���� ���� ==> ã�� ��� 
   @RequestMapping("recipe/find.do")  // RequestMapping -> GET/POST ��� ���ÿ� �۵� ����
//   													     => �˻����� ������ ������ ��� 
   public String recipe_find(String fd, String page, Model model) // fd => ����ڰ� ���� ������ / model => ������ ������
   {
	   if(fd==null)
		   fd="����";
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map = new HashMap();
	   map.put("start", (curpage*20)-19);
	   map.put("end", (curpage*20));
	   map.put("fd", fd);
	   
	   // DAO ����
	   List<RecipeVO> list = rService.recipeFindData(map);
	   int totalpage = rService.recipeFindTotalPage(map);
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("fd", fd);
	   model.addAttribute("main_jsp", "../recipe/find.jsp");
	   return "main/main";
   }
   
}