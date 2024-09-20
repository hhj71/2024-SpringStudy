<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style type="text/css">
 .container{
 	margin-top: 50px;
 }
 .row{
 	margin: 0px auto;
 	width:960px;
 }
 </style>
</head>
<body>
	<div class="container">
	<div class="row">
	<c:forEach var="vo" items=${list }>
     <div class="col-md-3">
      <div class="thumbnail">
        <a href="#">
          <img src="http://www.menupan.com${vo.poster }" style="width:230px; height:150px">
          <div class="caption">
            <p>{vo.name}</p>
          </div>
        </a>
      </div>
    </div>
    </c:forEach>
	</div>
	<div style="height: 20px"></div>
	<div class="row">
		
	
	</div>
	</div>
</body>
</html>