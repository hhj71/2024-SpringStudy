package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class EmpDAO {
	@Autowired	// ��ü�� �ּҰ��� ������ ����(�Ϲ� ������ ����� �Ұ���)
	private EmpMapper mapper; // Spring => MyBatis���� ���� => ������ Ŭ������ �ּҸ�
	/* @Value("ȫ�浿")
	private String name=""; */
}
