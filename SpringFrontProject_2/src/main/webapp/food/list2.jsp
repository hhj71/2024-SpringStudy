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
p{
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class = "container">
	<div class="row">
	<div class="col-md-3" v-for=>
		    <div class="thumbnail">
		      <a href="#">
		        <img src="http://www.menupan.com" style="width:230px;height: 150px">
		        <div class="caption">
		          <p class="a"></p>
		        </div>
		      </a>
		    </div>
		  </div>
		  </div>
		  <div style="height:10px;"></div>
		  <div class="row">
		  	<div class="text-center">
		  		<ul class="pagination">
		  		</ul>
		  	</div>
		  </div>
		  </div>
		  <script>
		  /*
		  		1. 라이브러리 로드
		  		   src =  
		  			   
		  		2. 태그 안에서 속성으로 제어
		  		v- : 디렉티브
		  		반복문 : v-for
		  		조건문 : v-if,
		  		기타 
		  			v-show="조건문" -> true(show), false{}
		  		
		  		
		  
		  */
		 
		  let foodApp=Vue.createApp({
			//Model => 데이터를 저장 => HTML로 전송
			data(){
				
			},
			// ViewModel => 데이터를 수정 => View로 변경된 데이터 전송
			// 사용자 정의 함수 / 라이브러리 함수 : 생명주기와 관련
			mounted(){
				// onload => 브라우저에 화면이 보이기 전 상태 
				// 서버 연결
				// list_vue.do?page=1
				this.dataRecv()
			},
			methods:{
				// 사용자 정의 함수 
				prev(){
					this.curpage= this.startPage-1
					this.dataRecv()
				},
				next(){
					this.curpage= this.endPage-1
					this.dataRecv()
				},
				dataRecv(){
					axios.get("http://localhost:8080/web/food/list_vue.do", {
						params:{
							page:this.curpage
						}
					}).then(response =>{
						console.log(response.data)
						this.food_list=response.data.list
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.startPage=response.data.startPage
						this.endPage=response.data.endPage
					})
					return{
					food_list:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0
				    }
				},
				
				range(start, end){
					let arr=[]
					let len = end-start
					for
				}
			}
		  }).mount("container")		  
		  </script>
</body>
</html>