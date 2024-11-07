package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// => �ڹٽ�ũ��Ʈ�� �ٸ� ��� �����Ҷ� ��� => ������������ �ʴ´� => ���ڿ� , JSON , JavaScript������ ���� 
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class BoardRestController {
   @Autowired
   private BoardDAO dao;
   
   @PostMapping(value="board/update_ok.do",produces = "text/html;charset=UTF-8")
   public String board_update_ok(BoardVO vo)
   {
	   String js="";
	   // �����ͺ��̽� ���� 
	   boolean bCheck=dao.boardUpdate(vo);
	   if(bCheck==true)
	   {
		  js="<script>"
			+"location.href=\"detail.do?no="+vo.getNo()+"\";"
			+"</script>"; 
		    
	   }
	   else
	   {
		   js="<script>"
			 +"alert(\"��й�ȣ�� Ʋ���ϴ�!!\");"
			 +"history.back();"
			 +"</script>";
	   }
	   return js;
   }
   
   @PostMapping(value="board/delete_ok.do",produces = "text/html;charset=UTF-8")
   // getParameter() => ������(DispatcherServlet)
   public String board_delete_ok(int no,String pwd)
   {
	   String js="";
	   boolean bCheck=dao.boardDelete(no, pwd);
	   if(bCheck==false)
	   {
		   js="<script>"
			 +"alert(\"��й�ȣ�� Ʋ���ϴ�!!\");"
			 +"history.back();"
			 +"</script>";
	   }
	   else
	   {
		   js="<script>"
		     +"location.href=\"list.do\";"
		     +"</script>";
	   }
	   return js;
   }
}