package com.sist.web;
/*			
 * 			 .do						매개변수  ====================
 * 	브라우저 ======== DispatcherServlet ========== Model
 *  				Controller					  처리 
 *  											 ========
 *  												|
 *  											 ========
 *  											  JSP 관리 => JSP찾아서 request 전송
 *  											 ========
 *  												|
 *  											  JSP로 전송 (View)
 * 	1. DispatcherServlet : 사용자의 요청을 받아서
 * 						   처리할 데이터를 넘겨주는 역할
 *  2. HandlerMapping : DispatcherServlet 로부터	받은 데이터를 처리
 *  					=> request에 담아서 전송
 *  					=> Model
 *  				    => @Controller: DispatcherServlet => 위임을 받아서 처리
 *  3.
 *  
 * 	4.
 * 
 *      									
 * 
 * 
 * 
 * 
 * 
 * 
 * 	 ================================= SpringFramework
 * 		= security
 * 		= batch : 스케쥴러
 * 		= data : 분석
 * 		
 * 		1. 라이브러리 추가 : pom.xml / gradle : boot에서 주로 사용
 * 						  maven		properties
 * 		2. 버전 변경 : 기본 1.6 ==> 1.8 이상
 * 		3. web.xml 변경
 * 		4. 패키지 => 클래스
 * 		5. 메모리 할당 요청
 * 		6. 클래스마다 어노테이션 올리기
 * 		7. DB 연동
 * 		8. JSP에서 화면 출력
 * 
 */
public class MainController {

}
