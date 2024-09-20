package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository("dao")
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper; // 스프링에서 구현한 클래스 주소를 받아온다
	public List<GoodsVO> goodsListData(Map map)
	{
		return mapper.goodsListData(map);
	}
	public int goodsTotalPage(Map map)
	{
		return mapper.goodsTotalPage(map);
	}
	
	public GoodsVO goodsDetailData()
	{
		
	}
}
