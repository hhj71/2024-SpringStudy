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
	<div class="container">
		<div class="row">
		 <form
			<select name="fd" class="input-sm">
				<option value="ename">이름</option>
				<option value="hiredate">입사년도</option>
				<option value="job">직위</option>
			</select>
			<input type=text name=ss size=20 class="input-sm">
			<button class="btn-sm btn-success">검색</button>
		</div>
	</div>

</body>
</html>