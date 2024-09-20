package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputVO ivo=new InputVO();
		ivo.setNo(1);
		ivo.setGno(10);
		ivo.setPrice(10000);
		
		StoreVO svo=new StoreVO();
		svo.setNo(1);
		svo.setGno(10);
		svo.setPrice(10000);
		
		GoodsDAO dao = new GoodsDAO();
		dao.insert(svo, ivo);
		System.out.println("정상수행");
	}

}
