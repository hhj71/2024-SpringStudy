package com.sist.service;
import java.util.*;
import com.sist.vo.*;
/*
 *       Controller ===== Service ====== Repository ====== Oracle 
 *                                       ========================
 *                                        |���� 
 *                            | �������� 
 */
public interface BoardService {
	   public List<ReplyBoardVO> boardListData(int start,int end);
	   public int boardRowCount();
	   public void boardInsert(ReplyBoardVO vo);
	   public ReplyBoardVO boardDetailData(int no);
}