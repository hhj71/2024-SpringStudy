package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// ȭ�鸸 ����
@Controller
public class DataBoardController {
	// Vue / React => ��ü ó�� => Route
	// Spring-boot : ThymeLeaf: html
	// =========== �����ϰ� ���� 
	// CDN => ���������� ���� => ���� 
	
	@GetMapping("databoard/list.do")
	public String databoard_list()
	{
		return "databoard/list";
	}
	@GetMapping("databoard/insert.do")
	public String databoard_insert()
	{
		return "databoard/insert";
	}
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model)
	{
		model.addAttribute("no", no); //${no}
		return "databoard/detail";
	}
}
