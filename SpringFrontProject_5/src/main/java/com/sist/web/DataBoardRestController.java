package com.sist.web;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;
// Vue�� ����
@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping(value="databoard/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String databoard_list(int page) throws Exception
	{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<DataBoardVO> list = dao.databoardListData(start, end);
		int count = dao.databoardCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		count = count-((rowSize*page)-rowSize);
		
		//Vue�� ������ ����
		Map map = new HashMap();
		map.put("list", list);
		map.put("count", count);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		// �ڹٽ�ũ��Ʈ ���� => Map�� JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
		
	}
		 /*
		   *   C:\\springDev\\springStudy2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringFrontProject_5\\upload
		   */
	 @PostMapping(value="databoard/insert_vue.do",produces = "text/plain;charset=UTF-8")
	  public String databoard_insert(DataBoardVO vo,HttpServletRequest request)
	  {
		  /*System.out.println(vo.getName());
		  System.out.println(vo.getSubject());
		  System.out.println(vo.getContent());
		  System.out.println(vo.getPwd());
		  System.out.println(vo.getFiles().size());
		  if(vo.getFiles().size()>0)
		  {
			  for(int i=0;i<vo.getFiles().size();i++)
			  {
				  System.out.println(vo.getFiles().get(i).getOriginalFilename());
			  }
		  }*/
		  String result="";
		  try
		  {
			 String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			 File dir=new File(path);
			 if(!dir.exists())
			 {
				 dir.mkdir();
			 }
			 path=path.replace("\\", File.separator); // ��,������: / , ������: \\
			 //System.out.println(path);
			 List<MultipartFile> list=vo.getFiles();
			 if(list==null) // ���ε尡 ���� ���
			 {
				 vo.setFilename("");
				 vo.setFilesize("");
				 vo.setFilecount(0);
			 }
			 else // ���ε尡 �ִ� ���� 
			 {
				 String filename="";
				 String filesize="";
				 for(MultipartFile mf:list)
				 {
					 String name=mf.getOriginalFilename();
					 File file=new File(path+name);
					 mf.transferTo(file); // ���ε� 
					 filename+=name+",";
					 filesize+=file.length()+",";
				 }
				 
				 filename=filename.substring(0,filename.lastIndexOf(","));
				 filesize=filesize.substring(0,filesize.lastIndexOf(","));
				 vo.setFilecount(list.size());
				 vo.setFilename(filename);
				 vo.setFilesize(filesize);
			 }
			 dao.databoardInsert(vo);
			 result="yes";  
		  }catch(Exception ex)
		  {
			  result=ex.getMessage();
		  }
		  return result;
	  }
	 
	  @GetMapping(value="databoard/detail_vue.do",produces = "text/plain;charset=UTF-8")
	  public String databoard_detail(int no) throws Exception
	  {
		  DataBoardVO vo=dao.databoardDetailData(no);
		  ObjectMapper mapper=new ObjectMapper();
		  String json=mapper.writeValueAsString(vo);
		  return json;
	  }
	  @GetMapping(value="databoard/download.do",produces = "text/plain;charset=UTF-8")
	  public void databoard_download(String fn,HttpServletResponse response,
			  HttpServletRequest request)
	  {
		  try
		  {
			  String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			  path=path.replace("\\", File.separator);
			  File file=new File(path+fn);
			  
			  response.setHeader("Content-Disposition", "attachment;filename="
					       +URLEncoder.encode(fn, "UTF-8"));
			  response.setContentLength((int)file.length());
			  // �������� ���� �д´� 
			  BufferedInputStream bis=
					  new BufferedInputStream(new FileInputStream(file));
			  // ����ڿ��� ������ ���� 
			  BufferedOutputStream bos=
					  new BufferedOutputStream(response.getOutputStream());
			  
			  int i=0; // ���� ����Ʈ �� 
			  byte[] buffer=new byte[1024];
			  while((i=bis.read(buffer, 0, 1024))!=-1)
			  {
				  bos.write(buffer, 0, i);
			  }
			  bis.close();
			  bos.close();
		  }catch(Exception ex) {}
	  }
	  
	  
	  
	  
	  @PostMapping(value="databoard/update_ok_vue.do", produces = "text/plain;charset=UTF-8")
	  public String databoard_update_ok(DataBoardVO vo, HttpServletRequest request)
	  {
		  String result="";
		  try
		  {
			  // �������� ���� ���� �б�
			  DataBoardVO fvo = dao.databoardFileInfoData(vo.getNo());
			  String path = request.getSession().getServletContext().getRealPath("/")
		  }
	  }
	}