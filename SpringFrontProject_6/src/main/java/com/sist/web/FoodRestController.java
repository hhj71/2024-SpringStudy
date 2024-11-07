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
	// DAO => ���������� �޸� �Ҵ� => getBean() ���� ��� ���� => ���������� �ּҰ��� ���Խ�������Ѵ� 
	// => �ڵ� ���� => @Autowired
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/list_vue.do", produces = "text/plain;charset=UTF-8")
	// => html: text/html (��ũ��Ʈ), => xml: text/xml , => json(�Ϲ� ���ڿ�): text/plain
	// JSON => �����͸� ��Ƽ� �ڹٽ�ũ��Ʈ�� ����
	// ��ü : {Ű:��, Ű:��...}(VO), ��� : [{},{},{}]... List
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
		// => Vue���� ó�� => [] => food_list: []
		map.put("curpage", page); // ����
		map.put("totalpage", totalpage); // ����
		map.put("type", type); // ���ڿ�
		
		// �ڹٽ�ũ��Ʈ���� �ν��� �����ϰ� => JSON���� ����
		
		ObjectMapper mapper=new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value="food/detail_vue.do", produces="text/plain;charset=UTF-8")
	
	{
		FoodVO vo=dao.foodDetailData(fno);
		//�� / ��
		String addr1=vo.getAddress();
		
		
		
		System.out.println("address:"+addr2.trim());
		List<FoodVO> list
		
				
	}
}
