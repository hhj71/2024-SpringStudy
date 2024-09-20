package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// => 자바스크립트나 다른 언어를 연결할 때 사용 => 파일을 제어하지 않는다 => 문자열, JSON, JavaScript

import com.sist.dao.*;
import com.sist.vo.*;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	// autowired -> 싱글톤 (여러개 사용해도 동일한 주소)
	
	@PostMapping(value="board/update_ok.do", produces="text/html; charset=UTF-8")
	public String board_update_ok(BoardVO vo)
	{
		String js="";
		// 데이터베이스 연동
		
	}
}
