package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

// ȭ�� ����� �Ұ��� => �ڹٽ�ũ��Ʈ / �ٸ� ���� ������ ����
// 								   ����� : Kotlin, Flutter, Dark ...			
// 					Ajax/Vue/React/Next
// 					������ ������ ó�� => MSA (Spring Cloud)

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;
//JSON, �Ϲ� ���ڿ�, ����...
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value="board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list(int page) throws Exception
	{
		int rowSize=10;
		int start = (rowSize*page)-(rowSize-1);
		int end = rowSize*page;
		
		List<BoardVO> list = dao.boardListData(start, end);
		int count = dao.boardTotalPage();
		int totalpage=(int)(Math.ceil(count/10.0));
		count = count-((rowSize*page)-rowSize);
		
		// ȭ�� ��� => ������ ���� (�ڹٽ�ũ��Ʈ => JSON)
		Map map = new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("count", count);
		map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
		
	}
	
	@PostMapping(value="board/insert_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_insert(BoardVO vo)
	{
		String result="yes";
		try
		{
			result="yes";
			dao.boardInsert(vo);
		}catch(Exception ex)
		{
			result="no";
		}
		return result;
	}
	
	@GetMapping(value="board/detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_detail(int no) throws Exception
	{
		//JSON - �����͸� ��� ������ ���
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(no);
		
		return json;
		
	}
	@GetMapping(value="board/update_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_update(int no) throws Exception
	{
		//JSON - �����͸� ��� ������ ���
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(no);
		
		return json;
	}
}
