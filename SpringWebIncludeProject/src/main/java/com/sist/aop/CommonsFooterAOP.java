package com.sist.aop;
/*
 * 		1. ������ = �����ӿ�ũ
 * 			= ���̺귯�� : ���� ���Ǵ� ��ɵ��� ��Ƽ� �̸� ������ Ŭ������ ����
 * 												==============
 * 						�����ڸ��� �ٸ��� ���� ����
 * 						 ==> ����� ���
 * 
 * 			= �����ӿ�ũ : �̸� ������ Ŭ������ ���� 
 * 						 �⺻ Ʋ�� ��������ִ�
 * 						 ===== �⺻ Ʋ�� �°� �����ؾ� �Ѵ�
 * 							   ======
 * 								ex) Controller => @GetMapping, @PostMapping	
 * 							    --> �����ڸ��� ������ �����ϰ� ����
 * 								==> ������
 * 								==> ������ => ���� ����
 * 				*** �������� ���ذ� �Ƿ¿� ������� ���� ������ ������ ����
 * 				*** �ʺ��ڵ� ���� �ð��� ������ �ʿ䰡 ���� ���������� ����
 * 				*** ���� => ���� �ð��� ������ �� �ִ�, MVC�� �̹� ������ �Ǿ��ִ� ���� => ��ɸ� �����ϸ� �ȴ�
 * 				*** ���� ����� ������ �ִ� = �н��ϱⰡ �ణ �ð��� �ɸ���
 * 				*** ���α׷��� ���̴� => �ӵ��� ���� 
 * 		1) �������� �⺻ ���
 * 			= DI
 * 				Ŭ������ ��Ƽ� �����ϴ� ���� (��ü�� �����ֱ� ����)
 * 				-----------------------
 * 				 �޸� �Ҵ� (new ��ü) => ����
 * 										===
 * 										�ʿ��� ��쿡�� ������� �ʱ�ȭ�� ����
 * 													============
 * 													1) setter DI
 * 														p: ~
 * 													2) ������ DI
 * 														c: ~
 * 									 => �Ҹ� 
 * 				=> �����ϴ� Ŭ���� : VO�� ����, �������̽� : ������ �Ұ���
 * 								  ======= ����� ���� ��������
 * 				   Ŭ���� �Ѱ��� ���� ���� ��û
 * 					=> <bean id="" class=""/>
 * 				   ��Ű�� ������ ����
 * 					=> <component-scan base-packag="��Ű����">
 * 						=> ���� 
 * 						=> Ŭ�������� ����
 * 							@Component : �Ϲ� Ŭ���� => MainClass, Jsoup ..
 * 							@Repository : DAO
 * 							@Service : DAO ������ ��� => BI
 * 							@Controller : Model (��û ó��) => ȭ�� ����
 * 							@RestController : Model (��û ó��) => �����͸� ����
 * 																 =============
 * 																  | Vue, Ajax
 * 							@ControllerAdvice : ���� ����ó��
 
 * 			= AOP : ���α׷� ���� => �ҽ��� �ߺ�, ���� ��� 
 * 									=> �ҽ� �ߺ� ����, �������� ���� => �ڵ�ȭ ó��
 * 									=> � �޼ҵ忡 � ��ġ�� �� ����� ���� 
 * 									   ========   ========
 * 										|PointCut	|joinPoint
 * 			= MVC
 * 						������ ��û (URL�ּ� �̿�)
 * 									  -> *.do   (��û �޴� ����)		 @GetMapping/@PostMapping���� �ش� URL�� ã�´�   return ���� �޾Ƽ� JSP�� ã�´�
 * 				Ŭ���̾�Ʈ  ===================== DispatcherServlet ====== HandelerMapping ======================== ViewResolver ==== JSP
 * 																				|��ûó��												|ȭ�� ���		
 * 																			  @Controller => Model
 * 																			  =========== ����Ŭ���� �����͸� �о�´�
 * 																				 |
 * 																			   1) ��û���� �޴´� : request�� �̿�
 * 																								 �Ű������� �̿� (����)
 * 																			   2) DB ����
 * 																					 ���� ���� === ������ �׼���
 * 																			   3) ����� 
 * 			= �����ͺ��̽� : ORM (MyBatis) 
 * 
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.service.*;
import com.sist.vo.*;
@Aspect // ������ => �ݺ����� => �޸� �Ҵ��� �ƴϴ�
@Component // �޸� �Ҵ�
public class CommonsFooterAOP {
	@Autowired
		private RecipeService rService;
		// ����Ǵ� ��ġ =>finally{�޼ҵ� ����} => ������ ������� ������ ����
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void commonsFooterData()
	{
		List<FoodHouseVO> foodList=rService.foodTop5Data(); 
		List<RecipeVO> recipeList=rService.recipeTop5Data();
		
		// ���� => request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("foodList", foodList);
		request.setAttribute("recipeList", recipeList);
	}
}
