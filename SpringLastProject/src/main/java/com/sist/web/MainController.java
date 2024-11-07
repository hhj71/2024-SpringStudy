package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.service.FoodService;
import com.sist.service.RecipeService;
/*
 *  ����
 *  ������
 *  	= DI => ���������� Ŭ������ �����ϴ� ����
 *  					 ==========
 *  					 1) Ŭ���� 1�� : Component
 *  					 2) Ŭ���� ������ ���� : Container
 *  						�������� �����̳ʴ� (Ŭ������ ���۵� : �淮 �����̳�)
 *  
 *  	  == Ŭ������ �����ϱ� ���ؼ��� ��ü���� / ��ü �Ҹ� (��ü�� �����ֱ� ����)
 *  	  == ��ü�����ÿ� ��������� �ʱ�ȭ�� �ʿ�ÿ� ���
 *  	  == ������ �ʱ�ȭ 
 *  		 ==========  setterDI => p:name => setName()
 *  		 ==========  ������ DI = c:
 *  
 *  	= AOP : ������ : ����ø��� ȣ���ϴ� ����� �ִ� ��쿡 �ڵ�ȣ���� ����
 *  			=> ����� ���� ���ٴ� Ʈ����� / ���� / �α�
 *  	= MVC : �� => ���̺귯��
 *  	  ===================
 *  	= ���̺귯�� : �ִ� �״�� ��� => ���� / � ��� / ã��
 *  	  ======= �������� �ʰ� ��� (ORM => ���̹�Ƽ��)
 *      ===================================================================
 *      �����ӿ�ũ : �⺻ ������ ���� Ʋ�� ������� �ִ� 
 *      		  => ���Ŀ� �°� �����ؼ� ���
 *      			 === xml / ������̼�
 *      		   1) ���̺귯�� �߰� : pom.xml / gradle
 *      ===================================================================
 *      MVC ����
 *      	Model : => @Controller / @RestController
 *      				=> ������� ��û�� �޾Ƽ� ó������� JSP�� �����ϴ� ����
 *      											  === @Controller
 *      											  ==== �ڹٽ�ũ��Ʈ�� ���� => @RestController
 *      	  => ���� : HandleMapping => �ش� �޼ҵ带 ã��
 *      
 *      	View : JSP(HTML)
 *      	  => ���� : ViewResolver => JSP�� ã�Ƽ� request�� ����
 *      
 *      	Controller : ����� ��û�� �޴� Ŭ���� : �̹� ���������� ����
 *      					=> DispatcherServlet : �޴��� ����
 *      					=> web.xml
 *      
 *      	WebApplicatonContext : ����� ���� Ŭ������ ����
 *      					=> Ŭ������ ���
 *      						application-context.xml
 *      						application-datasource.xml
 *      						application-security.xml
 *      					=> �Ѱ��ִ� ���
 *      						<init-param>
 *      							<param-name>contextConfigLocation</param-name>
 *      							<param-value>/WEB-INF/config/application-*.xml</param-value>
 *      						</init-param>
 *      													 ��ûó���ϴ� �޼ҵ� ã��
					                            => @GetMapping(URI)
					                            => @PostMapping(URI)
					                            => @RequestMapping(URI)
	     ����� ��û  ==> DispatcherServlet => HandleMapping => Modeló��
	                                                            | request
	                                                       ViewResolver 
	                                                         = p:prefix => ��θ�
	                                                         = p:suffix => Ȯ����
	                                                            | request
	                                                           JSP 
	       .do
	       
	       ��û�� ���� ó�� 
	       =============
	        1) �����ͺ��̽� (���̹�Ƽ��)  =====> DAO / Service
	                                    ���̺� 1��  ���õ� DAO�������� ��� ó��
	        2) �ܺ� API (����,���� , ����)
	        3) ���� 
	           redirect : ������ �ִ� �޼ҵ带 ��ȣ��   _ok (DML=>insert/update/delete)
	            => return "redirect:list.do"
	           forward : �ش� ������ ���� (select)
	            => return "���/���ϸ�"
	       ȭ�� ��� 
	       ========
	        1) JSP => EL/JSTL 
	        2) �ڹٽ�ũ��Ʈ : Ajax / VueJS / ReactJS
	           | 
	           List => []
	           VO   => {}
	           ============ JSON (jackson) => spring-boot������ �ڵ� ó��
	           �Ϲݵ������� : ����/�Ǽ�/��/����
	           
	       => �⺻���� 
	       => ���� : xml���� 
	       => Model / DAO / Service / JSP
	       => �⺻ �̷� : ���� ==> �ǹ��� ���� 
 *      
 */
import com.sist.vo.*;
//jsp ����
@Controller
@RequestMapping("main/")
public class MainController {
	// �ʿ��� Ŭ���� => ���������� ������ �´� (��ü �ּ�)
	@Autowired
	private RecipeService rService;
	
	@Autowired 
	private FoodService fService;
	
	// ����ڿ� ��û ���� => ó��
	@GetMapping("main.do")
	public String main_main(Model model)
	{
		   RecipeVO rvo=rService.recipeMaxHitData();
		   List<RecipeVO> rList=rService.recipeHitTop8();
		   List<FoodVO> fList=fService.foodHitTop5();
		   
		   model.addAttribute("rvo", rvo);
		   model.addAttribute("rList", rList);
		   model.addAttribute("fList", fList);
		
		return "main";
	}
}
