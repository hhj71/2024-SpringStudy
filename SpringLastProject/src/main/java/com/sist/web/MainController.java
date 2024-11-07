package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//jsp ����
@Controller
@RequestMapping("main/")
public class MainController {
	// �ʿ��� Ŭ���� => ���������� ������ �´� (��ü �ּ�)
	// ����ڿ� ��û ���� => ó��
	@GetMapping("main.do")
	public String main_main()
	{
		return "main/main";
	}
}
