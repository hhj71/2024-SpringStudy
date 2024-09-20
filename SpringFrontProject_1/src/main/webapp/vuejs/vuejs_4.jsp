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
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>	<div class="container">
		<h3 class="text-center">실시간 박스오피스</h3>
		<div class="row">
			<div class="col-sm-7">
			 <div class="col-md-6" v-for="vo in movie_list">
			 <div class= "thumbnail">
			 <img :src="'https://www.kobis.or.kr'+vo.thumbUrl" >
			 </div> 
			 </div>
			</div>
		</div>
		<div class="">
		
	</div>
	</div>
	<script>
		
	</script>
</body>
</html>