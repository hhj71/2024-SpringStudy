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
 *   public void aaa(){} => before
 *                    public void bbb(){} => after-throwing
 *                    public void ccc(){} => after
 *                    public void ddd(){} => after-returning
 *                    
 *                    public void display_1()
 *                    {
 *                       try
 *                       {
 *                           aaa() => before
 *                       }catch(Exception e)
 *                       {  
 *                           bbb() => after-throwing
 *                       }
 *                       finally
 *                       {
 *                           ccc() => after
 *                       }
 *                       
 *                       return ddd() => after-returning
 *                    }
 *                    public void display_2()
 *                    {
 *                       try
 *                       {
 *                           
 *                       }catch(Exception e)
 *                       {
 *                           
 *                       }
 *                       finally
 *                       {
 *                           
 *                       }
 *                       
 *                       return 
 *                    }
 *                    public void display_3()
 *                    {
 *                       try
 *                       {
 *                           
 *                       }catch(Exception e)
 *                       {
 *                           
 *                       }
 *                       finally
 *                       {
 *                          
 *                       }
 *                       
 *                       return  
 *                    }
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
 * 																					 ���� ���� === ������ �׼��� ����
 * 																			   3) ����� ����
 * 																				 @RequestMapping("member/join_ok.do")
 *public String member_join_ok(MemberVO vo)      
 *{
 *   dao.insert(vo)
 *}                   
  public String member_join_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String id=request.getParameter("id");
	  String pwd=request.getParameter("pwd");
	  String name=request.getParameter("name");
	  String sex=request.getParameter("sex");
	  String birthday=request.getParameter("birthday");
	  String post=request.getParameter("post");
	  String addr1=request.getParameter("addr1");
	  String addr2=request.getParameter("addr2");
	  String email=request.getParameter("email");
	  String content=request.getParameter("content");
	  String phone1=request.getParameter("phone1");
	  String phone2=request.getParameter("phone2");
	  
	  MemberVO vo=new MemberVO();
	  vo.setId(id);
	  vo.setPwd(pwd);
	  vo.setName(name);
	  vo.setSex(sex);
	  vo.setEmail(email);
	  vo.setBirthday(birthday);
	  vo.setPost(post);
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  vo.setContent(content);
	  vo.setPhone(phone1+")"+phone2);
	  MemberDAO.memberInsert(vo);

 *          
 *        = �����ͺ��̽� : ORM (MyBatis) 
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
		List<FoodVO> foodList=rService.foodTop5Data(); 
		List<RecipeVO> recipeList=rService.recipeTop5Data();
		
		// ���� => request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("foodList", foodList);
		request.setAttribute("recipeList", recipeList);
	}
}
