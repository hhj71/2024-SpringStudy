package com.sist.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.FoodHouseService;
import com.sist.vo.FoodVO;
@Controller
public class FoodController {
	@Autowired
	  private FoodHouseService fService;
	@GetMapping("food/main.do")
	  public String food_main(String page,Model model,HttpServletRequest request)
	  {
		  if(page==null)
		    	page="1";
		    int curpage=Integer.parseInt(page);
		    Map map=new HashMap();
		    map.put("start", (curpage*20)-19);
		    map.put("end", curpage*20);
		    List<FoodVO> list=fService.FoodHouseListData(map);
		    int count=fService.FoodHouseRowCount();
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
		    List<FoodVO> cList=new ArrayList<FoodVO>();
		    // ��Ű ��� List
		    if(cookies!=null)
		    {
		    	// �ֽź��� ��´�
		    	for(int i=cookies.length-1;i>=0;i--)
		    	{
		    		if(cookies[i].getName().startsWith("food_"))
		    		{
		    			String fno=cookies[i].getValue();
		    			FoodVO vo=fService.FoodHouseCookieInfoData(Integer.parseInt(fno));
		    			cList.add(vo);
		    		}
		    	}
		    }
		    model.addAttribute("cList", cList);
		    model.addAttribute("size", cList.size());
		  // include�� JSP�� ���� 
		    model.addAttribute("main_jsp", "../food/main.jsp");
		  return "main/main";
	  }
	@GetMapping("food/detail_before.do")
	   public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra)
	   {
		   // Cookie ���� => ���� => ������ ����
		   // ��Ű�� ������ HttpServletResponse response�� �Ű������� �޾ƿ;� �Ѵ�. 
		   // ���� ��ü => Model : forward
		   // ���� => RedirectAttributes : sendRedirect
		   Cookie cookie = new Cookie("food_"+fno, String.valueOf(fno));
		   // ��Ű�� ���� ��ġ�� ������ , ���ڿ��� ������ ���� 
		   //							Ű	   ,	 ��
		   //						  getName()	    getValue()
		   cookie.setMaxAge(60*60*24); // �ʴ��� ���� => ���� �Ⱓ
		   cookie.setPath("/"); // ���� ��ġ
		   // �������� ����
		   response.addCookie(cookie);
		   ra.addAttribute("fno", fno);
		   // ra.addFlashAttribute("no", no); ==> �����͸� ����� �� �� ���
		   return "redirect:../food/detail.do";
	   }
	   @GetMapping("food/detail.do")
	   // recipe/detail.do?no=
	   public String food_detail(int fno,Model model)
	   {
		   // �����ͺ��̽� ���� => �����͸� �б� 
		   FoodVO vo=fService.FoodHouseDetailData(fno);
		   
		   // detail.jsp�� ����� ������ �����ش� 
		   model.addAttribute("vo", vo);
		   
		   // Model�� JSP�� ���۽ÿ� ��� : forward
		   model.addAttribute("main_jsp", "../food/detail.jsp");
		   return "main/main";
	   }
	   @GetMapping("food/cookie_all.do")
	   public String food_cookie_all(HttpServletRequest request, Model model)
	   {
		// ��Ű ���
		    Cookie[] cookies=request.getCookies();
		    List<FoodVO> cList = new ArrayList<FoodVO>();
		    
		    if(cookies!=null)
		    {
		    	//�ֽź��� ��´�
		    	for(int i=cookies.length-1; i>=0; i--)
		    	{
		    		if(cookies[i].getName().startsWith("food_"))
		    		{
		    			String fno = cookies[i].getValue();
		    			FoodVO vo = fService.FoodHouseCookieInfoData(Integer.parseInt(fno));
		    			cList.add(vo);
		    		}
		    	}
		    }
		   model.addAttribute("cList",cList);
		   model.addAttribute("size", cList.size());
		   model.addAttribute("main_jsp", "../food/cookie_all.jsp");
		   return "main/main";
	   }
	   @GetMapping("food/cookie_delete.do")
	   public String food_cookie_delete(HttpServletRequest request, HttpServletResponse response)
	   {
		   Cookie[] cookies= request.getCookies();
		   if(cookies!=null)
		   {
			   for(int i=cookies.length-1; i>=0; i--)
		    	{
		    		if(cookies[i].getName().startsWith("food_"))
		    		{
		    			 cookies[i].setPath("/");
						   cookies[i].setMaxAge(0);// ��Ű ���� 
						   response.addCookie(cookies[i]); // �������� �˸� 
					   }
		    	}	
		   }
		   return "redirect:../food/main.do";
	   }
}
