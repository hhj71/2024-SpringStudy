package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// ���� ��� 
@Aspect // �޸� �Ҵ��� �ϴ� ������̼��� �ƴϴ� <aop:aspect>
@Component
public class BoardAOP {
   @Around("execution(* com.sist.web.*Controller.*(..))")
   public Object logtime(ProceedingJoinPoint jp)
   {
	   Object obj=null;
	   try
	   {
		   long start=System.currentTimeMillis();
		   obj=jp.proceed();
		   System.out.println("��û �޼ҵ�:"+jp.getSignature().getName());
		   long end=System.currentTimeMillis();
		   System.out.println("��û ó�� �ҿ� �ð�:"+(end-start));
	   }catch(Throwable ex){}
	   return obj;
   }
   
}