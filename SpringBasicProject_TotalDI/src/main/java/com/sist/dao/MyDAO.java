package com.sist.dao;

public class MyDAO {
		// getConnection  / disConnection()
		// �������� ���Ǵ� �κ�
		//==================================
	public void getConnection()
	{
		System.out.println("����Ŭ ����");
	}
	public void disConnection()
	{
		System.out.println("����Ŭ ����");
	}
	//======================================== AOP���
	// AOP�� OOP���� ���� ���ϴ� 
	public void select()
	{
//		getConnection();
		System.out.println("SELECT ���� ó��");
//		disConnection();
	}
	public void insert()
	{
//		getConnection();
		System.out.println("INSERT ���� ó��");
//		disConnection();
	}
	public void update()
	{
//		getConnection();
		System.out.println("UPDATE ���� ó��");
//		disConnection();
	}
	public void delete()
	{
//		getConnection();
		System.out.println("DELETE ���� ó��");
//		disConnection();
	}
	public String find (String fd)
	{
		return fd+"ã�� �Ϸ�!";
	}
	public void afterReturning(Object obj)
	{
		System.out.println("======= ����� �ڵ� ó��=======");
		System.out.println("�����:"+obj);
		
	}
}
