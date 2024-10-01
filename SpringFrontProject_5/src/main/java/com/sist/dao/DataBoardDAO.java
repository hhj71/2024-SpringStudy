package com.sist.dao;

import com.sist.vo.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository
public class DataBoardDAO {
	 @Autowired 
	 private DataBoardMapper mapper;
	 
	 public List<DataBoardVO> databoardListData (int start, int end)
		{
			return mapper.databoardListData(start, end);
		}
	 public int databoardCount()
	 {
		 return mapper.databoardCount();
	 }
	 public void databoardInsert(DataBoardVO vo)
	   {
		   mapper.databoardInsert(vo);
	   }
	 public DataBoardVO databoardDetailData(int no)
	 {
		 mapper.hitIncrement(no);
		 return mapper.databoardDetailData(no);
	 }
	 public String databoardUpdate(DataBoardVO vo)
	 {
		 
	 }
	}
