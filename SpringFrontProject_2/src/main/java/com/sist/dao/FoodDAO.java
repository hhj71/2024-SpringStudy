package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 * 	1. 스프링 어노테이션 
 * 		= 메모리 할당 => 스프링에 클래스 관리 
 * 							  ========= 생성 ~ 소멸 => 필요시에는 주소값을 얻어서 사용
 * 			구분
 * 			==========
 * 			DAO	===========> @Repository : 저장소, 데이터베이스 연결
 * 			SERVICE =======> @Service : BI (DAO 여러개를 통합)
 * 			MODEL   =======> 화면 제어 : @Controller
 * 							  자바스크립트 / 코틀린 다른 언어와 연결 : @RestController
 * 							  ==============================  JSON
 * 			Exception => 공통 예외 처리 => 모든 모델 클래스의 예외처리 한번에 처리
 * 						 @ControllerAdvice  / @RestControllerAdvice
 * 			일반 클래스 : OpenAPI => @Component
 		
 		= 모든 클래스를 메모리 할당 요청하지 않는다
 * 		  ~VO : 사용자 정의 데이터형
 * 		  인터페이스는 메모리 할당을 하지 않는다
 * 		= 공통으로 사용되는 기능을 모아서 관리 => 공통모듈
 * 		  @Aspect => AOP
 * 
 * 		= 자바로 환경설정 => @Bean
 * 		= React연결 : port가 다르다 (서버연결을 할 수 없다 => 포트 허용)		
 * 
 * 
 */
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper; // 스프링에서 구현된 객체 주소를 받는다 => 자동 주입
	
	public List<FoodVO> foodListData(int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	
	
}
