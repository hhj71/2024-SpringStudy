package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 * 	1. ������ ������̼� 
 * 		= �޸� �Ҵ� => �������� Ŭ���� ���� 
 * 							  ========= ���� ~ �Ҹ� => �ʿ�ÿ��� �ּҰ��� �� ���
 * 			����
 * 			==========
 * 			DAO	===========> @Repository : �����, �����ͺ��̽� ����
 * 			SERVICE =======> @Service : BI (DAO �������� ����)
 * 			MODEL   =======> ȭ�� ���� : @Controller
 * 							  �ڹٽ�ũ��Ʈ / ��Ʋ�� �ٸ� ���� ���� : @RestController
 * 							  ==============================  JSON
 * 			Exception => ���� ���� ó�� => ��� �� Ŭ������ ����ó�� �ѹ��� ó��
 * 						 @ControllerAdvice  / @RestControllerAdvice
 * 			�Ϲ� Ŭ���� : OpenAPI => @Component
 		
 		= ��� Ŭ������ �޸� �Ҵ� ��û���� �ʴ´�
 * 		  ~VO : ����� ���� ��������
 * 		  �������̽��� �޸� �Ҵ��� ���� �ʴ´�
 * 		= �������� ���Ǵ� ����� ��Ƽ� ���� => ������
 * 		  @Aspect => AOP
 * 
 * 		= �ڹٷ� ȯ�漳�� => @Bean
 * 		= React���� : port�� �ٸ��� (���������� �� �� ���� => ��Ʈ ���)		
 * 
 * 
 */
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper; // ���������� ������ ��ü �ּҸ� �޴´� => �ڵ� ����
	
	public List<FoodVO> foodListData(int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	
	
}
