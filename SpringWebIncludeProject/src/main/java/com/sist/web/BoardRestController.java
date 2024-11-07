package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;
// Controller => JSP���� => JSP���ϸ� , .do
// RestController => �ڹٽ�ũ��Ʈ ���� , ��Ʋ�� ���� => �ٸ� ��� ����
//  => �Ϲ� ���ڿ� , JSON 
@RestController  // @ResponseBody (�����) => @RestController ����� ���� ��

public class BoardRestController {
    @Autowired
	private BoardService bService;
    @PostMapping("board/update_ok.do")
    public String board_update_ok(ReplyBoardVO vo)
    {
    	String result="no";
    	result=bService.boardUpdate(vo);
    	return result;
    }
    @PostMapping("board/delete_ok.do")
    public String board_delete_ok(int no,String pwd)
    {
    	String result="no";
    	result=bService.boardDelete(no, pwd);
    	return result;
    }
}