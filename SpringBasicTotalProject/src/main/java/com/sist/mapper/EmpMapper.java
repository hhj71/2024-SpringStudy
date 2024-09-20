package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;


public interface EmpMapper {
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD DY')as dbday "
			+ "sal, deptno FROM emp")
	public List<EmpVO> empListData();
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD DY')as dbday "
			+ "sal, deptno FROM emp WHERE empno=#{empno}")
	public EmpVO empDetailData();
	
	@Select("SELECT DISTINCT ename FROM emp")
	public List<String> empEnameList();
	
	@Select({"<script>"
			+ "SELECT empno, ename, job, TO_CHAR(hiredare, 'YYYY-MM-DD')ad dbday"
			+ "sal, deptno FROM emp "
			+ "<if test=\"fd!"
			+ "WHERE ${id} LIKE '%'||#{ss}||'%'"
			+ "</if>"
			+ "</script>"})
	
	public List<EmpVO> empNameFindData(Map map);
	// 다중 조건 => HRD, 시청 프로그램... 잡포털
	
	
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD')as dbday, "
			+ "sal, deptno "
			+ "FROM emp "
			+ "WHERE ${fd} LIKE '%'||#{ss}||'%'")
	public List<EmpVO> empFindData(Map map);	 
			
	
	
}
