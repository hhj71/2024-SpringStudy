package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// => �ڹٽ�ũ��Ʈ�� �ٸ� �� ������ �� ��� => ������ �������� �ʴ´� => ���ڿ�, JSON, JavaScript

import com.sist.dao.*;
import com.sist.vo.*;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	// autowired -> �̱��� (������ ����ص� ������ �ּ�)
	
	@PostMapping(value="board/update_ok.do", produces="text/html; charset=UTF-8")
	public String board_update_ok(BoardVO vo)
	{
		String js="";
		// �����ͺ��̽� ����
		
	}
}
