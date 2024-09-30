<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
   margin-top: 50px
}
.row{
   margin: 0px auto;
   width: 960px
}
p{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
<script src="image.js"></script>
<script src="page.js"></script>
</head>
<body>
	<div class="contanier">
	<div class="row">
		<poster-card></poster-card>
	</div>
	<div style="height:10px"></div>
	<div class="row">
	<div class="text-center">
		<page-card></page-card>
	</div>
	</div>
</div>
<script>
	
</script>
</body>
</html>