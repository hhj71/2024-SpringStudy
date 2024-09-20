package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Model => 사용자 요청 처리 => 사용자 정의 ~Model , 스프링 => ~controller, 

@Aspect // 공통모듈 => 메모리 할당은 할 수 없다 => 자동 호출 (Callback)
public class FoodController {

}

@GetMapping("list.do")
public String food_list(String page, Model model)
{	
	// Model => 전송 객체 (request 대신 JSP로 값을 전송하는 클래스)
	// 사용자가 전송한 값은 매개변수를 통해서 값을 받는다
	// request : Cookie 사용시
	// response : Cookie 사용, 파일 업로드
	if(page==null)
		page="1";
	int curpage = Integer.parseInt(page);
	int rowSize=20;
	int start = (rowSize*curpage)-(rowSize-1);
	int end = rowSize*curpage;
	List<FoodVO> list = dao.foodListData(start, end);
	int totalpage = dao.foodTotalPage();
	final int BLOCK=10;
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	
	
	
	
	//전체 객체 => Model
	//request, response => 가급적이면 사용하지 않는다
	// =================== IP가 존재 (보안)
	model.addAttribute("curpage", curpage);
	model.addAttribute("totalpage", totalpage);
	model.addAttribute("startPage", startPage);
	model.addAttribute("endPage", endPage);
	model.addAttribute("list", list);
	return "food/list"; // 파일명만 지정
}
@PostMapping("member/insert1.do")
public String member_insert1(HttpServletRequest request, HttpServletResponse response)
{
	
	
	return "member/detail";
}
@PostMapping("member/insert2.do")
public String member_insert2(String name, String sex, String address, String phone, String email)
{
	return "member/detail";
}