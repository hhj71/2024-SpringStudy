package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.EmpDAO;
import com.sist.vo.EmpVO;

@Controller
public class MainController {
	@Autowired
	private EmpDAO eDao;
	
	@GetMapping("main/main.do")
	public String main_main(Model model)
	{
		// 1.�����ͺ��̽� ����
		List<EmpVO> list = eDao.empListData();
		// 2.������� �о => JSP�� ����
		model.addAttribute("list", list);
		return "main/main";
	}
	
	
	@GetMapping("main/find.do")
	public String emp_find(String fd, String ss, Model model)
	{
		if(fd==null)
			fd="all";
		//�����ͺ��̽� ����
		Map map = new HashMap();
		map.put("fd", fd);
		map.put("ss", ss.toUpperCase());
		List<EmpVO> list = eDao.
	}
}
