package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
//	<select> = @Select
	@Select("SELECT empno, ename, job, hiredatem sal FROM emp")
	public List<EmpVO> empListData();
//	 	   ===========	========== =
//			resultType	 id		    parameterType
//  @Results => <resultMap>	
}
