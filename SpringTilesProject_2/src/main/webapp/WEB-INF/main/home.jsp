<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">






</div>

<div class="row">
<div class="text-center">
<ul class=pagination>
<c:if test="${startPage>1 }">
	
</c:if>

	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<li ${i==curpage?"class=active":""}><a href="../main/main.do?page=${i}">${i }</a></li>
	</c:forEach>
	
	</ul>
	</div>
	</div>
</body>
</html>