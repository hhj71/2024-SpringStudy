package com.sist.vo;
import java.util.*;

import lombok.Data;

@Data
public class EmpBVO {
	private int empno, sal;
	private String ename, job;
	private Date hiredate;
}
