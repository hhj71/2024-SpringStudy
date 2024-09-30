package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.FoodVO;

@Service
public class FoodHouseServiceImpl implements FoodHouseService{
	@Autowired
    private FoodHouseDAO dao;

	
	@Override
	public List<FoodVO> FoodHouseListData(Map map) {
		// TODO Auto-generated method stub
		return dao.FoodHouseListData(map);
	}

	@Override
	public int FoodHouseRowCount() {
		// TODO Auto-generated method stub
		return dao.FoodHouseRowCount();
	}

	@Override
	public FoodVO FoodHouseCookieInfoData(int fno) {
		// TODO Auto-generated method stub
		return dao.FoodHouseCookieInfoData(fno);
	}

	@Override
	public FoodVO FoodHouseDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.FoodHouseDetailData(fno);
	}

}
