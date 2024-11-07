package com.sist.web;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/*
 *   =========================== ȭ�� 
 *   @Controller : ȭ�� ���� => � ȭ���� �������� ���� 
 *                 ======= ����� ������ ���� : Model 
 *                         addAttribute()
 *   @RestController : �ڹٽ�ũ��Ʈ ���� , JSON => Ajax , Vue , React 
 *                     => ȭ��� ������ ����
 *                     
 *   ����ϴ� ���� => ���������� ���� 
 *   ========== �޼ҵ� ���� 
 *              �޼ҵ�� ���� 
 *              = ������ 
 *                 String : forward , redirect 
 *                            |          |
 *                          ������ ����   ������ ��ϵ� ȭ�� �̵�
 *                                      return "redirect:list.do"
 *                                      => _ok.do 
 *                          return "��θ�/���ϸ�"
 *                 void : �ٿ�ε� 
 *              = �Ű����� : => DispatcherServlet���� �Ű����� ���� 
 *                        getParameter() => �Ű������� ó�� 
 *                        => ~VO������ ���� �޴´� => insert/update/join...
 *                           ================ Ŀ�ǵ� ��ü 
 *                        => ��� ������ : String => �ش� �������� 
 *                           ==================
 *                           1. ���� ���� ��� : String => page�� �޴� ��� 
 *                                                      �˻���
 *                                            ======================= ����Ʈ ����
 *                        => String[] : checkbox�� �̿��ؼ� ���� �޴� ��� 
 *                        => ���� ��ü 
 *                           HttpServletRequest => �������� ����
 *                              => ������ ����� IP,PORTȮ���� ���� 
 *                              => ���� : Cookie�� �б�  
 *                           HttpServletResponse => �������� ����
 *                              => ���� : Cookie ���� => �������� ���� 
 *                              => �ٿ�ε� 
 *                           HttpSession => Login / session�� �ʿ��� ��ġ 
 *                           RedirectAttributes => redirect�� ���� ���� 
 *                            return "redirect:detail.do?no="+no
 *                               ra.addAttribute("no",no)
 *                            => return "redirect:detail.do";
 *                           Model : request�� ��ü�ϴ� ���� ��ü 
 *                           => Cookie�� ���尴ü�� �ƴϴ� (request�� �̿��Ѵ�)
 *                     => �Ű������� �������� ������ ���� 
 *                           
 *              = �޼ҵ�� : �����Ӱ�.. => URL�ּҸ� ��Ī
 *   =========================== �����ͺ��̽� ����   
 *   @Repository : ���̺� �Ѱ��� ���� => ���� 
 *   @Service : DAO�� ������ �ִ� ��� 
 *              emp / dept 
 *              board / reply 
 *   ===========================  ���� ó�� 
 *   @ControllerAdvice : ���� ����ó�� => WEB������ ���� 
 *   
 *   => ���������� �ַ� ���Ǵ� ��Ű�� 
 *      com.sist.vo : ����� ���� �������� => �����ͺ��̽� �÷��� => ����Ŭ�κ��� ������ ����
 *      com.sist.dao : �����ͺ��̽� ���� 
 *      com.sist.service : DAO�� ������ ��� ���
 *      com.sist.manager : ��õ , ũ�Ѹ� , �ܺε����� �б� => OpenAPI
 *                         ���� , �ǽð� �˻��� ....(Naver,Kakao)
 *      com.sist.web : Model => ���� => ����� ���� 
 *      com.sist.intercept : �߰��� ó���� ���� 
 *                                                  | (intercept) => �ڵ� �α���
 *        ����� ��û (.do) ===== DispatcherServlet ====== HandlerMapping 
 *                                                            |
 *                                                       com.sist.web
 *                                                       @GetMapping
 *                                                       @PostMapping
 *                                                       @RequestMapping 
 *                                                       => URL�ּҸ� �̿��ؼ� �޼ҵ带 ȣ�� 
 *                                                     (intercept)- | ����� (Model) 
 *                                                       ViewResolver
 *                                                  (intercept)    - | request�� ��ȯ 
 *                                                           JSP
 *                                                            
 *      com.sist.aop : �������� ��� => ��û ó�� ���� �ð� 
 *                     => footer�� ��� : �ڵ�ȭ 
 *      com.sist.commons : ����ó���� �ѹ��� ó�� 
 *      com.sist.security : login (���ٰź�) , ���Ѻο� => �޴� ���� 
 *                          remember-me : �ڵ� �α��� 
 *                          ��й�ȣ : ��ȣȭ 
 *      com.sist.chat : webchat : �ǽð� ä�� 
 *                        | ������ ��ü ä�� 
 *                        | ����� ä�� => 1:1ä�� 
 *      ================================================
 *      ��Ű���ȿ��� ����ϴ� Ŭ���� => �����ϴ� ����(������) 
 *                               === Ŭ���� �޸� �Ҵ� ===== �Ҹ� (�޸� ����)
 *      * DispatcherServlet 
 *        =================
 *        1. @WebServlet("*.do") => ���������� �������� �ʴ´� 
 *           => web.xml�� ��� 
 *              ======= ��Ĺ���� �о�� ���� 
 *                => ������ JSP�� ��Ĺ�� ���� ���� 
 *                   ========== ã�� ��� : URL
 *                => request�� ������� �ʴ´� 
 *                   => �ѱ� ��ȯ => ��� 
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.DataBoardVO;
import java.net.*;
@Controller
@RequestMapping("databoard/") // �������� ���Ǵ� URL�ּҸ� ��Ƽ� => ����
public class DataBoardController {
   @Autowired
   private BCryptPasswordEncoder encoder;
   @Autowired
   private DataBoardDAO dao;
   
   // ����� ��û�� ���� ó�� 
   @GetMapping("list.do")
   public String databoard_list(String page,Model model)
   {
	   //String en=encoder.encode("1234");
	   //System.out.println(en);
	   
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   List<DataBoardVO> list=dao.databoardListData(start, end);
	   // �������� 
	   int count=dao.databoardRowCount();
	   int totalpage=(int)(Math.ceil(count/(double)rowSize));
	   count=count-((rowSize*curpage)-rowSize);
	   // ����ڷκ��� �޴� �� => Model : ����� ���� ��ü 
	   // ��¿� �ʿ��� �����͸� list.jsp�� ���� 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("count", count);
	   model.addAttribute("list", list);
	   
	   return "databoard/list";
   }
   // ������ �߰� 
   @GetMapping("insert.do")
   public String databoard_insert()
   {
	   return "databoard/insert";
   }
   // ���� ������ ÷�� 
   @PostMapping("insert_ok.do")
   public String databoard_insert_ok(DataBoardVO vo)
   {
	   String en=encoder.encode(vo.getPwd());// ��ȣȭ 
	   vo.setPwd(en);
	   String path="c:\\spring_upload";
	   List<MultipartFile> list=vo.getFiles();
	   if(list==null)
	   {
		   vo.setFilename("");
		   vo.setFilesize("");
		   vo.setFilecount(0);
	   }
	   else // ���ε尡 �ִ� ���
	   {
		  try
		  {
			   String filename="";
			   String filesize="";
			   for(MultipartFile mf:list)
			   {
				   String name=mf.getOriginalFilename(); // ����ڰ� ������ ���ϸ�
				   File file=new File(path+"\\"+name);
				   mf.transferTo(file);// ���� ���ε� 
				   filename+=file.getName()+",";
				   filesize+=file.length()+",";
			   }
			   filename=filename.substring(0,filename.lastIndexOf(","));
			   filesize=filesize.substring(0,filesize.lastIndexOf(","));
			   
			   vo.setFilename(filename);
			   vo.setFilesize(filesize);
			   vo.setFilecount(list.size());
		  }catch(Exception ex){}
	   }
	   dao.databoardInsert(vo);
	   return "redirect:list.do";
   }
   // �󼼺��� 
   @GetMapping("detail.do")
   public String databoard_detail(int no,Model model)
   {
	   DataBoardVO vo=dao.databoardDetailData(no);
	   if(vo.getFilecount()!=0)
	   {
		   List<String> nList=new ArrayList<String>();
		   List<String> cList=new ArrayList<String>();
		   String[] names=vo.getFilename().split(",");
		   String[] lens=vo.getFilesize().split(",");
		   nList=Arrays.asList(names);
		   cList=Arrays.asList(lens);
		   // �迭 => List�� ���� 
		   
		   model.addAttribute("nList", nList);
		   model.addAttribute("cList", cList); // a.jpg(1000byte)
	   }
	   model.addAttribute("vo", vo);
	   return "databoard/detail";
   }
   // �ٿ�ε� 
   @GetMapping("download.do")
   public void databoard_download(String fn,HttpServletResponse response)
   {
	   try
	   {
		   String path="c:\\spring_upload";
		   // �������� ������ �о �Ѱ��� �޸𸮿� ���� => �ӵ��� ������ 
		   BufferedInputStream bis=
				   new BufferedInputStream(new FileInputStream(path+"\\"+fn));
		   // �ٿ�ε� ��û�� Ŭ���̾�Ʈ 
		   BufferedOutputStream bos=
				   new BufferedOutputStream(response.getOutputStream());
		   
		   // 1.�ٿ�ε�â�� �����ش� 
		   response.setHeader("Content-Disposition", "attachment;filename="
				     +URLEncoder.encode(fn,"UTF-8"));
		   File file=new File(path+"\\"+fn);
		   response.setContentLength((int)file.length());// ���α׷�����
		   
		   // ���� �ٿ�ε� 
		   byte[] buffer=new byte[1024]; // TCP:1024 , UDP : 512
		   int i=0; // ���Ͽ��� ���� ����Ʈ ũ�� 
		   while((i=bis.read(buffer, 0, 1024))!=-1)
		   {
			   // -1 : EOF
			   bos.write(buffer, 0, i);
		   }
		   bis.close();
		   bos.close();
	   }catch(Exception ex){}
   }
   
   @GetMapping("delete.do")
   public String databoard_delete_ok(int no,String pwd,Model model)
   {
	   DataBoardVO vo=dao.databoardFileInfoData(no);
	   boolean bCheck=dao.databoardDelete(no, pwd);
	   model.addAttribute("bCheck", bCheck);   
	   try
	   {
		   String files=vo.getFilename();
		   if(vo.getFilecount()!=0) // ������ �ִ� ��� 
		   {
			   StringTokenizer st=new StringTokenizer(files,",");
			   while(st.hasMoreTokens())
			   {
				   File file=new File("c:\\spring_upload\\"+st.nextToken());
				   file.delete();
			   }
		   }
	   }catch(Exception ex) {}
	   return "databoard/delete_ok";
   }
}




