package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodHouseDAO;
import com.sist.vo.FoodHouseVO;

@Controller
public class FoodHouseController {
	
	@Autowired
	private FoodHouseDAO dao;
	
	//매칭 => 사용자 전송 URL => 메소드 호출
	@GetMapping("foodhouse/list.do")
	 public String FoodHouse_list(String page,Model model)
	   {
	      if(page==null)
	         page="1";
	      int curpage=Integer.parseInt(page);
	      Map map=new HashMap();
	      map.put("start", (curpage*20)-19);
	      map.put("end", curpage*20);
	      List<FoodHouseVO> list=dao.FoodHouseListData(map);
	      int count=dao.FoodHouseRowCount();
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
	      
	      return "foodhouse/list";
	   }
	   
	
}
