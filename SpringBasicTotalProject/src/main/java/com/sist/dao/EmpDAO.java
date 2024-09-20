package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class EmpDAO {
	@Autowired	// 객체의 주소값만 주입이 가능(일반 변수는 사용이 불가능)
	private EmpMapper mapper; // Spring => MyBatis에서 구현 => 구현한 클래스의 주소를
	/* @Value("홍길동")
	private String name=""; */
}
