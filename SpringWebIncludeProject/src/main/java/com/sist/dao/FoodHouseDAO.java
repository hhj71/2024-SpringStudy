package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodHouseMapper;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;

@Repository
public class FoodHouseDAO {
	@Autowired
    private FoodHouseMapper mapper;
	
	  public List<FoodVO> FoodHouseListData(Map map)
	    {
	    	return mapper.FoodHouseListData(map);
	    }
	    public int FoodHouseRowCount()
	    {
	    	return mapper.FoodHouseRowCount();
	    }
	    public FoodVO FoodHouseDetailData(int fno)
	    {
	    	mapper.FoodHouseHitIncrement(fno);
	    	return mapper.FoodHouseDetailData(fno);
	    }
	  //쿠키에 출력할 데이터 
	    public FoodVO FoodHouseCookieInfoData(int fno)
	    {
	    	return mapper.FoodHouseCookieInfoData(fno);
	    }
	
}
