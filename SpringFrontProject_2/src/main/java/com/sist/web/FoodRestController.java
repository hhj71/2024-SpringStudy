package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

// 화면 출력에 필요한 데이터 전송 => 자바스크립트와 연결 (return => json)
// 화면 제어는 할 수 없다

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	@GetMapping("food/list_vue.do")
	public String food_list(String page) throws Exception {
	if(page==null)
		page="1";
	int curpage = Integer.parseInt(page);
	int rowSize=20;
	int start = (rowSize*curpage)-(rowSize-1);
	int end= rowSize*curpage;
	
	List<FoodVO> list = dao.foodListData(start, end);
	int totalpage=dao.foodTotalPage();
	
	final int BLOCK =10;
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endPage>totalpage)
		endPage=totalpage;
	
	Map map = new HashMap();
	map.put("list", list);
	map.put("curpage", page);
	map.put("totalpage", totalpage);
	map.put("startPage", startPage);
	map.put("endPage", endPage);
	
	//JSON 변경 => Boot에서는 자동처리
	ObjectMapper mapper=new ObjectMapper();
	String json=mapper.writeValueAsString(map);
	return json;
	}
	
	@GetMapping(value="food/find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find(String address, int page) throws Exception
	{
		
	}
}
