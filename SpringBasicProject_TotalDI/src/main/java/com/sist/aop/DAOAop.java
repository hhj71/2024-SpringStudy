package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class DAOAop {
	public void before()
	{
		System.out.println("오라클 연결");
	}
	public void after()
	{
		System.out.println("오라클 해제");
	}
	// around
	// 핵심 코딩 => 
	// => 핵심 처리 / 공통 처리
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 처리
	 * ====================
	 * public void execution()
	 * {
	 * 		before로 등록된 메소드 호출 ==> 
	 * 		    모든 메소드, 여러개 메소드에 공통으로 적용이 되는 
	 * 			
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		Object obj = null;
		long start = System.currentTimeMillis();
		System.out.println("호출된 메소드:"+jp.getSignature().getName());
		obj=jp.proceed(); // 사용자가 요청한 메소드를 호출
		long end = System.currentTimeMillis();
		System.out.println("소요시간:"+(end-start));
		return obj;
	}
	public void afterRetruning(Object obj)
	{
		System.out.println("DELECT문장 처리");
	}
}
