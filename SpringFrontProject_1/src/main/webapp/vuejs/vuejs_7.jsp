<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="container">
	<div class="row">
		Start: <input type= text size=5 class="input-sm">
		&nbsp;
		End: <input type= text size=5 class="input-sm">
		<div class="text-center">
			
	 </div>
	</div>
</div>
<script>
let app = Vue.createApp({
 data(){
	 return {
		 startPage:0,
		 endPage:0
		 
	 }
 },	
// 페이지 나눠서 반복문 수행
	methods:{
		range(start, end){
			let arr=[]
			let len = end-start
			for(let i=0; i<=len; i++)
				{
				    arr[i] = start
				    start++
				}
			return arr
		}
	}

}).mount(".container")



</script>
</body>
</html>