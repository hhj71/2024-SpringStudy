package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Model => ����� ��û ó�� => ����� ���� ~Model , ������ => ~controller, 

@Aspect // ������ => �޸� �Ҵ��� �� �� ���� => �ڵ� ȣ�� (Callback)
public class FoodController {

}

@GetMapping("list.do")
public String food_list(String page, Model model)
{	
	// Model => ���� ��ü (request ��� JSP�� ���� �����ϴ� Ŭ����)
	// ����ڰ� ������ ���� �Ű������� ���ؼ� ���� �޴´�
	// request : Cookie ����
	// response : Cookie ���, ���� ���ε�
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
	
	
	
	
	//��ü ��ü => Model
	//request, response => �������̸� ������� �ʴ´�
	// =================== IP�� ���� (����)
	model.addAttribute("curpage", curpage);
	model.addAttribute("totalpage", totalpage);
	model.addAttribute("startPage", startPage);
	model.addAttribute("endPage", endPage);
	model.addAttribute("list", list);
	return "food/list"; // ���ϸ� ����
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