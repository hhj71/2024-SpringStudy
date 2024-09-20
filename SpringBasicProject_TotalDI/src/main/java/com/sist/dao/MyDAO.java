package com.sist.dao;

public class MyDAO {
		// getConnection  / disConnection()
		// 공통으로 사용되는 부분
		//==================================
	public void getConnection()
	{
		System.out.println("오라클 연결");
	}
	public void disConnection()
	{
		System.out.println("오라클 해제");
	}
	//======================================== AOP대상
	// AOP는 OOP에서 하지 못하는 
	public void select()
	{
//		getConnection();
		System.out.println("SELECT 문장 처리");
//		disConnection();
	}
	public void insert()
	{
//		getConnection();
		System.out.println("INSERT 문장 처리");
//		disConnection();
	}
	public void update()
	{
//		getConnection();
		System.out.println("UPDATE 문장 처리");
//		disConnection();
	}
	public void delete()
	{
//		getConnection();
		System.out.println("DELETE 문장 처리");
//		disConnection();
	}
	public String find (String fd)
	{
		return fd+"찾기 완료!";
	}
	public void afterReturning(Object obj)
	{
		System.out.println("======= 결과값 자동 처리=======");
		System.out.println("결과값:"+obj);
		
	}
}
