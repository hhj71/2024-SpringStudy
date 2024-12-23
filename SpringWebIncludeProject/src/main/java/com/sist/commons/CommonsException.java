package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// 공통 예외 처리 => 모든 Controller 예외 처리
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex)
	{
		System.out.println("========== 데이터베이스 에러 발생 ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex)
	{
		System.out.println("========== 파일 입출력 에러 발생 ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(RuntimeException.class)
	public void RunException(RuntimeException ex)
	{
		System.out.println("========== 실행시 에러 발생 ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex)
	{
		System.out.println("========== 기타 에러 발생 ==========");
		ex.printStackTrace();
		System.out.println("=======================================");
	}
	
}
