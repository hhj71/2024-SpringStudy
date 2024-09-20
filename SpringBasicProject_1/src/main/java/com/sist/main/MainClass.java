package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//1. 컨테이너에 XML 파싱 요청
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app1.xml");
		//2. 필요한 객체를 요청
		Board b = (Board)app.getBean("board");
		System.out.println("b="+b);
		Board b1=app.getBean("board", Board.class);
		System.out.println("b1="+b1);
	}
}
