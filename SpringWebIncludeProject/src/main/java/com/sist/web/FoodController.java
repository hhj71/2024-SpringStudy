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
		    
		    // 쿠키 출력 
		    Cookie[] cookies=request.getCookies();
		    List<FoodVO> cList=new ArrayList<FoodVO>();
		    // 쿠키 담는 List
		    if(cookies!=null)
		    {
		    	// 최신부터 담는다
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
		  // include할 JSP를 지정 
		    model.addAttribute("main_jsp", "../food/main.jsp");
		  return "main/main";
	  }
	@GetMapping("food/detail_before.do")
	   public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra)
	   {
		   // Cookie 제작 => 저장 => 브라우저 전송
		   // 쿠키를 쓰려면 HttpServletResponse response를 매개변수로 받아와야 한다. 
		   // 전송 객체 => Model : forward
		   // 전송 => RedirectAttributes : sendRedirect
		   Cookie cookie = new Cookie("food_"+fno, String.valueOf(fno));
		   // 쿠키의 저장 위치는 브라우저 , 문자열만 저장이 가능 
		   //							키	   ,	 값
		   //						  getName()	    getValue()
		   cookie.setMaxAge(60*60*24); // 초단위 저장 => 저장 기간
		   cookie.setPath("/"); // 저장 위치
		   // 브라우저로 전송
		   response.addCookie(cookie);
		   ra.addAttribute("fno", fno);
		   // ra.addFlashAttribute("no", no); ==> 데이터를 감춰야 할 때 사용
		   return "redirect:../food/detail.do";
	   }
	   @GetMapping("food/detail.do")
	   // recipe/detail.do?no=
	   public String food_detail(int fno,Model model)
	   {
		   // 데이터베이스 연결 => 데이터를 읽기 
		   FoodVO vo=fService.FoodHouseDetailData(fno);
		   
		   // detail.jsp로 출력할 데이터 보내준다 
		   model.addAttribute("vo", vo);
		   
		   // Model은 JSP로 전송시에 사용 : forward
		   model.addAttribute("main_jsp", "../food/detail.jsp");
		   return "main/main";
	   }
	   @GetMapping("food/cookie_all.do")
	   public String food_cookie_all(HttpServletRequest request, Model model)
	   {
		// 쿠키 출력
		    Cookie[] cookies=request.getCookies();
		    List<FoodVO> cList = new ArrayList<FoodVO>();
		    
		    if(cookies!=null)
		    {
		    	//최신부터 담는다
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
						   cookies[i].setMaxAge(0);// 쿠키 삭제 
						   response.addCookie(cookies[i]); // 브라우저에 알림 
					   }
		    	}	
		   }
		   return "redirect:../food/main.do";
	   }
}
