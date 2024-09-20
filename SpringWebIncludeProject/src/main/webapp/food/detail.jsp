<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
  <h2 class="sectiontitle">맛집 상세보기</h2> 
  <table class="table">
   <tr>
     <td width="30%" class="text-center" rowspan="6">
      <img src="https://www.menupan.com${vo.poster }" style="width: 100%">
     </td>
   </tr>
    <tr>
     <td colspan="2">
      <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
     </td>
   </tr>
   <tr>
     <th width="20%" class="text-center">주소</th>
     <td width="50%">${vo.address }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">전화</th>
     <td width="50%">${vo.phone }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">음식종류</th>
     <td width="50%">${vo.type }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">테마</th>
     <td width="50%">${vo.theme }</td>
   </tr>
  </table>
  <table class="table">
   <tr>
    <td>${vo.content }</td>
   </tr>
    <td class="text-right">
      <a href="#" class="btn btn-xs btn-success">예약</a>
      <a href="#" class="btn btn-xs btn-info">찜하기</a>
      <a href="#" class="btn btn-xs btn-warning">좋아요</a>
      <a href="../food/main.do" class="btn btn-xs btn-primary">목록</a>
    </td>
   </tr>
  </table>
  </main>
  </div>
</body>
</html>