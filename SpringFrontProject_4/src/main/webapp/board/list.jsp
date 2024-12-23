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
   margin-top: 50px
}
.row{
   margin: 0px auto;
   width: 800px
}
</style>
<!--  
		ES5 => type="text/javascript"
		ES6 => type="text/babel" => 생략

 -->
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">자유 게시판(VueJS)</h3>
		<div class="row">
			<table class="table">
				<tr>
			        <td>
			         <a href="insert.do" class="btn btn-sm btn-primary">새글</a>
			        </td>
		       </tr>
				<tr> 
				 <th width=10% class="text-center">번호</th>
				 <th width=45% class="text-center">제목</th>
				 <th width=15% class="text-center">이름</th>
				 <th width=20% class="text-center">작성일</th>
				 <th width=10% class="text-center">조회수</th>
				 </tr>
				<tr v-for="vo in board_list"> 
				 <td width=10% class="text-center">{{vo.no}}</td>
				 <td width=45%><a :href="'detail.do?no='+vo.no">{{vo.subject}}</a>
				 	&nbsp;<sup v-if="today===vo.dbday"><img src="new.gif"></sup>
				 </td>
				 <td width=15% class="text-center">{{vo.name}}</td>
				 <td width=20% class="text-center">{{vo.dbday}}</td>
				 <td width=10% class="text-center">{{vo.hit}}</td>
				 </tr>
				 <tr>
				 	<td colspan="5" class="text-center">
				 	<input type=button class="btn btn-sm btn-success" value="이전"
				 	@click= "prev()">
				 		{{curpage}} page / {{totalpage}} pages
				 	<input type=button class="btn btn-sm btn-info" value="다음"
				 	@click= "next()">
				 	</td>
				 	</tr>
			</table>
		</div>
	</div>
	<script>
	//
		let boardApp= Vue.createApp({
			data(){
				// 멤버 변수 설정 = 서버에서 전송한 데이터와 일치해야 함
				return {
					board_list:[],
					curpage:1,
					totalpage:0,
					count:0,
					today:''
				}	
			},
			//onload => 브라우저에 실행전에 => 서버에서 데이터를 받는다
			//Model에 설정된 데이터를 변경 => ViewModel
			// MVVM => 패턴을 이용
			mounted(){ // 시작과 동시에 처리할 때 사용
				//서버에서 전송한 데이터 읽기 => 멤버변수에 저장
				this.dataRecv()
			},
			//사용자 정의 메소드
			methods:{ 
				dataRecv(){
					
					axios.get('http://localhost:8080/web/board/list_vue.do',{
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.board_list=response.data.list
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
	        			this.count=response.data.count
	        			this.today=response.data.today
					})	
				},
				prev(){
					this.curpage= this.curpage>1?this.curpage-1:this.curpage
					this.dataRecv()
				},
				next(){
					this.curpage= this.curpage<this.totalpage?this.curpage+1:this.curpage
					this.dataRecv()
				}
			}
		}).mount('.container')
	</script>
</body>
</html>