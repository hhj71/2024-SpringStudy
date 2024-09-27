package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

@RestController
public class FoodRestController {
	// DAO => 스프링에서 메모리 할당 => getBean() 이제 사용 안함 => 스프링에서 주소값을 대입시켜줘야한다 
	// => 자동 주입 => @Autowired
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/list_vue.do", produces = "text/plain;charset=UTF-8")
	// => html: text/html (스크립트), => xml: text/xml , => json(일반 문자열): text/plain
	// JSON => 데이터를 모아서 자바스크립트로 전송
	// 객체 : {키:값, 키:값...}(VO), 목록 : [{},{},{}]... List
	// JSON => Ajax, Vue, React, Next... kotlin / flutter
	public String food_list(int page, String type) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("type", type);
		//#{type} ==> map.get("type")
		List<FoodVO> list = dao.foodTypeListData(map);
		int totalpage=dao.foodTypeTotalPage(type);
		
		map = new HashMap();
		map.put("list", list); // model.addAttribute("list", list) => EL, JSTL
		// => Vue에서 처리 => [] => food_list: []
		map.put("curpage", page); // 정수
		map.put("totalpage", totalpage); // 정수
		map.put("type", type); // 문자열
		
		// 자바스크립트에서 인식이 가능하게 => JSON으로 변경
		
		ObjectMapper mapper=new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value="food/detail_vue.do", produces="text/plain;charset=UTF-8")
	
	{
		FoodVO vo=dao.foodDetailData(fno);
		//구 / 시
		String addr1=vo.getAddress();
		
		
		
		System.out.println("address:"+addr2.trim());
		List<FoodVO> list
		
				
	}
}
