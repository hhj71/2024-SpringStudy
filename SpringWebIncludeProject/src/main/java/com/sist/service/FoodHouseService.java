package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.FoodVO;


public interface FoodHouseService {
	public List<FoodVO> FoodHouseListData(Map map);
	public int FoodHouseRowCount();
	public FoodVO FoodHouseDetailData(int fno);
	public FoodVO FoodHouseCookieInfoData(int fno);
}
