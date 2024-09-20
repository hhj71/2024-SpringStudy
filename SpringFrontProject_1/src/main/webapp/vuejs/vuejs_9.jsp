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
<body>
	<div class="container">
		<div class="row">
		 <input type= text size=20 class="input-sm">
		 <input type= button class="btn-sm btn-danger" value="검색"
		 @click="find()">
		 
		</div>
	</div>
 <script>
 /*
 *
 
 	M: 데이터 설정 => data()
 	V: v-if, v-model, v-on:click , v-for , v-show ...
 	   디렉티브
 	VM :  
 		 mounted(){} , methods:{}, components:{}, filter
 
 */
	let app= Vue.createApp({
	 data(){
	 	return {
	 	   fd:''
	 	   }
	 	 },
	 	methods:{
	 		// id => ref의 속성을 이용
	 		// this.$refs.ref명.focus(), value... => input tag 제어
	 		// => e.target.value => React
	 		// => $('#fd').val() => jquery
	 		// submit 버튼은 사용하지 않는다 => 자체에서 CRUD를 처리
	 		find(){
	 			let fds = this.$refs.fd.value
	 		}
	 	}
	})
	</script>
</body>
</html>