package com.sist.main;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("�޴� ����(�̸� �˻�(1), �ּ� �˻�(2), �������� �˻�(3):");
		int menu = scan.nextInt();
		String column="";
		if(menu==1)
			column="name";
		else if(menu==2)
			column="address";
		
	}

}
