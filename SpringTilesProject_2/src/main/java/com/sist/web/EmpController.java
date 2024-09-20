package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;


@Controller
public class EmpController {
	// ���������� ��ü �ּҸ� ���� ��� (������ => Ŭ���� ������) => �޼ҵ� �ȿ����� ���� �� ����
	// �ʿ��� ��ü�� ����� �޴´� => Service, DAO, Manager 
	@Autowired // ���������� ��ü�� �´� �ּҸ� �ڵ����� ���� => �������� �̱����� ��� => ���� ����
	private EmpDAO eDao;
	
	// ����� ��û���� ó�� => @GetMapping / @PostMapping / @RequestMapping
	// 													===============
	//													������ 6 => ����
	// 												* ������ 6 => Ÿ��� ����
	// �� => �ܼ��ϴ� (����� ��û�� => ��û ó��(�����ͺ��̽� ����) => �����ִ� ��)	
	//				  �Ű�����		MyBatis					addAttribute()
	// ��ٱ��� => id, ��ǰ��ȣ, ����
	// �ݵ� 	  => id, �ݵ���ȣ, ���
	@GetMapping("emp/list.do")
	//<form> ajax => @PostMapping => ���� (�α���, ȸ������) => �����͸� ���� ����
	public String emp_list(Model model)
	{
		// ������ ���̽� ���� => Mapper(���� -> �ڵ� ����) ==> DAO
		List<EmpVO> list=eDao.empListData();
		model.addAttribute("list", list);
		return "emp/list";
	}
	// emp/detail.do?empno=${vo.empno}
	@GetMapping("emp/detail.do")
	public String emp_detail(int empno, Model model)
	{
		EmpVO vo= eDao.empDetailData(empno);
		model.addAttribute("vo", vo);
		return "emp/detail";
	}
}
