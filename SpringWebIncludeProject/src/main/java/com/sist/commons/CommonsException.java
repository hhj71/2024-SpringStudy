package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// ���� ���� ó�� => ��� Controller ���� ó��
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex)
	{
		System.out.println("========== �����ͺ��̽� ���� �߻� ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex)
	{
		System.out.println("========== ���� ����� ���� �߻� ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(RuntimeException.class)
	public void RunException(RuntimeException ex)
	{
		System.out.println("========== ����� ���� �߻� ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex)
	{
		System.out.println("========== ��Ÿ ���� �߻� ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	
}
