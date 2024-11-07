package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class DataBoardVO {
  private int no,hit,filecount;
  private String name,subject,content,pwd,filename,filesize,dbday;
  private Date regdate;
  // Update File�� �����ϴ� List
  // => files[0] , files[1] ......  => �迭 ����
  private List<MultipartFile> files;
}
