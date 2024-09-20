package com.sist.dao;

import java.util.ArrayList;

public class EmpDAO {

	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try 
		{
			//getConnection() => AOP => Before
			String sql = "SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD'),sal "
						 +"FROM emp ORDER BY empno ASC";
			ps= conn.prepareStatement(sql);
		}catch(Exception ex)
		{
			
		}
		finally
		{
			
		}
		return list;
    }
	public EmpVO empDetailData(int empno)
	{
		EmpVO vo = new EmpVO();
		
	}
}
