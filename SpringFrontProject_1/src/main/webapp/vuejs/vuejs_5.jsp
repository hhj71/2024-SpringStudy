<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.moiveTr:hover{
	cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
<div class="col-sm-5">
<table class="table">
	<tr v-for="vo in movie_list">
		

</table>
</div>
</body>
</html>