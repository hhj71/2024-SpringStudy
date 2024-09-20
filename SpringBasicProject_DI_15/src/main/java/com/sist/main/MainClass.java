package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;

@Component("mc")
// MainClass는 스프링에서 관리 => 필요한 경우에는 반드시 스프링을 통해서 객체를 얻어온다
// 스프링 : MainClass mc =                                                                                                                                                                           
public class MainClass {
	@Autowired
	private GoodsDAO dao;
	public static void main(String[] args) {
		ApplicationContext.app= new ClassPathXmlApplicationContext("application-*.xml");
		
	}
	
}
