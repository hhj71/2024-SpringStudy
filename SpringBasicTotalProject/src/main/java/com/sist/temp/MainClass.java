package com.sist.temp;

import java.util.*;

public class MainClass {
			public static void main(String[] args) {
				List<SawonVO> list = new ArrayList<SawonVO>();
				SawonVO hong = SawonVO.newInstance();
				System.out.println("hong="+hong);
				hong.setName("ȫ�浿");
				hong.setDept("���ߺ�");
				list.add(hong);
				SawonVO shim = SawonVO.newInstance();
				System.out.println("shim="+shim);
				shim.setName("��û��");
				shim.setDept("��ȹ��");
				list.add(shim);
				SawonVO park = SawonVO.newInstance();
				System.out.println("park="+park);
				park.setName("�ڹ���");
				park.setDept("������");
				list.add(park);
				
				for(SawonVO vo:list)
				{
					System.out.println();
				}
			}
}
		