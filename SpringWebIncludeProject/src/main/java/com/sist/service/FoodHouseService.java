package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.FoodHouseVO;


public interface FoodHouseService {
	public List<FoodHouseVO> FoodHouseListData(Map map);
	public int FoodHouseRowCount();
	public FoodHouseVO FoodHouseDetailData(int fno);
	public FoodHouseVO FoodHouseCookieInfoData(int fno);
}
