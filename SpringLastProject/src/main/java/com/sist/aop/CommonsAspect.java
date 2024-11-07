package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;

@Aspect // ������
@Component // �޸��Ҵ�
public class CommonsAspect {
	@Autowired
	private FoodService fservice;
	
	//after => finally �� ����� ���� => ������ ����
	/*
	 * 	DispatcherServlet
	 * 		=> ���� Ŭ���� 
	 * 			@Controller / @RestController
	 * 
	 */
	@After("execution(* com.sist.web.*Controller.*(..))") // ��� controller(restcontroller ����)
	public void footerCookieSend()
	{
		// request�� ������ �´�
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list = new ArrayList<FoodVO>();
		if(cookies!=null)
		{
			for(int i=cookies.length-1; i>=0; i--)
			{
				//Cookie(key, value) => Cookie("food_1", "1")
				if(cookies[i].getName().startsWith("food_"))
				{
					String fno = cookies[i].getValue();
					FoodVO vo=fservice.foodInfoData(Integer.parseInt(fno));
					list.add(vo);
				}
			}
		}
		request.setAttribute("cList", list);
	}
}
