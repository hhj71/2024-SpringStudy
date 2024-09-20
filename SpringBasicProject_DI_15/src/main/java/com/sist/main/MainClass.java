package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;

@Component("mc")
// MainClass�� ���������� ���� => �ʿ��� ��쿡�� �ݵ�� �������� ���ؼ� ��ü�� ���´�
// ������ : MainClass mc =                                                                                                                                                                           
public class MainClass {
	@Autowired
	private GoodsDAO dao;
	public static void main(String[] args) {
		ApplicationContext.app= new ClassPathXmlApplicationContext("application-*.xml");
		
	}
	
}
