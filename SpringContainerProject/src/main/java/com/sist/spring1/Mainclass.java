package com.sist.spring1;
/*
 * 		C/S
 * 		client   /   server
 * 		   |			|
 * 		FRONT 		  BACK
 * 						�������� ===> Ŭ���̾�Ʈ���� ���� �߻� ==> 
 * 
 * 
 */
public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello = new Hello();//  ���õ� ����� ��Ƽ� ����
		hello.sayHello("ȫ�浿"); // �Ѱ��� ����� ������ �ִ�
		// ���ռ��� ���� ���α׷� 
		// ������ => �ٸ� Ŭ������ ����
		// �������̸� => new�� ������� �ʴ´� (new => ���ռ��� ���� ���α׷����� ����ȴ�)
	}

}
