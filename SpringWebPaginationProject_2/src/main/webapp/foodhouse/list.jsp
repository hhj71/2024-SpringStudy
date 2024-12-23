<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
    margin: 0px auto;
    width: 960px;
}
 p{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
</head>
<body>
  <div class="container">
   <h3>총 <span style="color:green; font-size: 40px;">
     <fmt:formatNumber value="${count }" pattern="#,###,###,###"/>
   </span>개의 맛집이 있습니다.</h3>
   <div class="row">
    <c:forEach var="vo" items="${list }">
            <div class="col-md-3">
               <div class="thumbnail">
                  <a href="detail.do?fno=${vo.fno }"> 
                  <img src="https://www.menupan.com/${vo.poster }" title="${vo.name }" style="width: 230px; height: 150px;">
                     <div class="caption">
                        <p>${vo.name }</p>
                     </div>
                  </a>
               </div>
            </div>
         </c:forEach>
   </div>
   <div style="height: 10px"></div>
   <div class="row">
     <div class="text-center">
       <ul class="pagination">
        <c:if test="${startPage>1 }">
         <li><a href="list.do?page=${startPage-1 }">&lt;</a></li>
        </c:if>
        
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
          <li ${i==curpage?"class=active":"" }><a href="list.do?page=${i }">${i }</a></li>
        </c:forEach>
        
        <c:if test="${endPage<totalpage }">
         <li><a href="list.do?page=${endPage + 1}">&gt;</a></li>
        </c:if>
       </ul>
     </div>
   </div>
  </div>
</body>
</html>