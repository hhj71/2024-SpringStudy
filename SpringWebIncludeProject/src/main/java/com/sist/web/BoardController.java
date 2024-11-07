package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.service.*;
import com.sist.vo.*;

// ������ => Service+VO+DAO => ����� ���� => JSP�� ����

@Controller // DispatcherServlet �� ����
/*
 * 		1. ��û �ޱ� => DispatcherServlet
 * 		2. Model�� ��ȸ => HandlerMapping
 * 		2-1. Model �޼ҵ� ȣ�� => HandlerMapping
 * 		3. JSP�� ã�´� => ViewResolver
 * 		3-1. JSP�� request�� ���� => ViewResolver
 * 		4. JSP�� request�� �޾Ƽ� ȭ�鿡 ���
 * 		5. ���������� �б�
 * 		
 * 	   Model ======> Service =======> DAO ======> ����Ŭ 
 *          <=======       <=======   |   <======
 *                                  ��������
 *          ���ռ��� ���� ���α׷� : �������� ����
 *          =================== �����ÿ� �ٸ� Ŭ������ ������ ���� ����� 
 *                              =================================
 *                               | *** POJO => �������� Ŭ����
 *                                      70%  
 * 
 * 		=> ������ : Model - ȭ������ : @Controller / ���������� : @RestController / @GetMapping, @PostMapping / ������: void, String / �Ű����� getParameter�� ��ü
 * 				  JSP - ȭ���� ���� => �ڹٽ�ũ��Ʈ (Ajax => Vue => React) / �ٸ� ȭ������ ���� => <a> , <form> 
 * 				  DAO - �������� <== Mapper(SQL)
 * 				  VO, Service
 * 
 * 
 * 
 */
public class BoardController {
	  // ��ü�� �̿��ؼ� @Autowired�� ����ϸ� �ּҰ��� ������ => �ӵ��� �ʴ� 
	   // �������̸� �����ڸ� �̿��Ѵ� 
		private BoardService bService;
		
		@Autowired
		public BoardController(BoardService bService)
		{
			this.bService=bService;
		}
		
		@GetMapping("board/list.do") // ����ڰ� �Խ��� ����� �����޶� ��û�ߴٸ� => ���ǹ�
		// ������̼���  if�� �߰��ϴ� ���̴�
		// ã�� => ���������� ã�Ƽ� ó��
		public String board_list(String page, Model model)
		{
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			Map map= new HashMap();
			int rowSize=10;
			int start =(rowSize*curpage)-(rowSize-1);
			int end = rowSize*curpage;
			List<ReplyBoardVO> list=bService.boardListData(start, end);
			int count = bService.boardRowCount();
			int totalpage = (int)(Math.ceil(count/(double)rowSize));
			count = count-((curpage*rowSize)-rowSize);
			
			 // 15/10 => 1.0
			   // 15/10.0 => 1.5 => 2
			   model.addAttribute("list", list);
			   model.addAttribute("curpage", curpage);
			   model.addAttribute("totalpage", totalpage);
			   model.addAttribute("count", count);
			   model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			   model.addAttribute("main_jsp", "../board/list.jsp");
			   return "main/main";
		}
		
		 @GetMapping("board/insert.do")
		   public String board_insert(Model model)
		   {
			   model.addAttribute("main_jsp", "../board/insert.jsp");
			   return "main/main";
		   }
		   @PostMapping("board/insert_ok.do")
		   public String board_insert_ok(ReplyBoardVO vo)
		   {
			   bService.boardInsert(vo);
			   return "redirect:../board/list.do";
		   }
		   // board/detail.do?no=${vo.no }
		   /*
		    *   �Ű����� : ������ ��� ���� 
		    *            ����ڰ� ��û�� �� 
		    *              => String / int / String[] / VO
		    *            ���̺귯�� Ŭ���� (���� ��ü)
		    *              => HttpServletRequest : Cookie �б� 
		    *              => HttpServletResponse : Cookie���� , �ٿ�ε� 
		    *              => HttpSession 
		    *              => ������ ���� : Model 
		    *              => sendRedirect�� ������ ���� 
		    *                 RedirectAttributes 
		    *              => ���� : ���� Ŭ���� 
		    *   ������ : String / void 
		    *           |         | ajax / �ٿ�ε� / �����췯 => task 
		    *           request�� ���� : forward => "��θ�/JSP��"
		    *           ������ ȭ�� �̵� : sendRedriect => "redirect:~.do"
		    *           => request�� �������� �ʴ´� 
		    *           ==================== ȭ�� ���� 
		    *   �޼ҵ�� : �����ڰ� ����  => @GetMapping => URL�ּ� 
		    *   
		    *   => detail.do?no=10 => ��� �����ʹ� String���� ���� �� �ִ� 
		    *      ===============
		    *        (String no) -> Integer.parseInt(no)
		    *        (int no)
		    *   => �����Ͱ� ���� ��� 
		    *      VO  *******
		    *      List ****** File��Ƽ ���ε�
		    *      String[] = checkbox
		    *      
		    *   => 404 => ������ ���� ��� : ��θ� , ���� ���� 
		    *      500 => �ҽ� ���� : SQL���� 
		    *             NULL�϶� String�޼ҵ� �̿� 
		    *      400 => Bad Request => �Ű������� ���������� �ٸ� ��� 
		    *      405 => GET = @GetMapping , POST => @PostMapping
		    *                                 <form> , ajax , axios.post
		    *      403 => ���� �ź� : ���� �ο� => security
		    *      412 => UTF-8  => �ѱ� ��ȯ �ڵ尡 Ʋ�� ���
		    *             UFT-8 
		    */
		   @GetMapping("board/detail.do")
		   public String board_detail(int no,Model model)
		   {
			   
			   // �����ͺ��̽� ���� 
			   ReplyBoardVO vo=bService.boardDetailData(no);
			   
			   // ����� ����
			   model.addAttribute("vo", vo);
			   model.addAttribute("main_jsp", "../board/detail.jsp");
			   // request.setAttribute()
			   /*
			    *   request/response����ϸ� �ȵǴ� ���� 
			    *   =================
			    *   | ����� ������ ������ �ִ� => IP,��ǻ�Ϳ� ���� ������ ���� ���� 
			    *   | ������ 5 => ���� 
			    *               ==== request,response��� �󵵰� ���� ���� 
			    *                    Cookie => @Cookie
			    *               ==== XML�� ������� �ʴ´� === �ڹ� ���������� ���� 
			    */
			   return "main/main";// forward => request���� => classȭ Model
		   }
		   // board/update.do?no=${vo.no }
		   @GetMapping("board/update.do")
		   public String board_update(int no,Model model)
		   {
			   ReplyBoardVO vo=bService.boardUpdateData(no);
			   model.addAttribute("vo", vo);
			   model.addAttribute("main_jsp", "../board/update.jsp");
			   return "main/main";
		   }
		   // board/reply.do?no=${vo.no }
		   @GetMapping("board/reply.do")
		   public String board_reply(int no,Model model)
		   {
			   model.addAttribute("no", no);
			   model.addAttribute("main_jsp", "../board/reply.jsp");
			   return "main/main";
		   }
		   @PostMapping("board/reply_ok.do")
		   public String board_reply_ok(int pno,ReplyBoardVO vo)
		   {
			   // ó�� 
			   bService.boardReplyInsert(pno, vo);
			   return "redirect:../board/list.do";
		   }
		   // board/delete.do?no=${vo.no }
		   @GetMapping("board/delete.do")
		   public String board_delete(int no,Model model)
		   {
			   model.addAttribute("no", no);
			   model.addAttribute("main_jsp", "../board/delete.jsp");
			   return "main/main";
		   }
		}
