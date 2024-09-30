<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link href='https://cdn.jsdelivr.net/npm/@fullcalendar/icalendar@5.11.3/main.css' rel='stylesheet' />
<script src="
https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js
"></script>

<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
   margin-top: 50px
}
.row{
   margin: 0px auto;
   width: 1110px
}
.nav-link{
	cursor:pointer;
}
p{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-center" width=30% height="500">
						<table class="table">
							<caption><h3>맛집 정보</h3></caption>
							<tr>
								<td class="text-center" colspan="2">
								<button class="btn-xs btn-danger" @click="typeChange('한식')">한식</button>
								<button class="btn-xs btn-danger" @click="typeChange('양식')">양식</button>
								<button class="btn-xs btn-danger" @click="typeChange('일식')">일식</button>
								<button class="btn-xs btn-danger" @click="typeChange('중식')">중식</button>
								<button class="btn-xs btn-danger" @click="typeChange('분식')">분식</button>
								</td>
							</tr>
							<tr v-for="vo in food_list">
								<td class="text-center" width=30%>
							
									
								
						</table>
					</td>
					<td class="text-center" width=40% height="500">
						<table class="table" v-show="isDay">
							<caption><h3>예약일 정보</h3></caption>
							<tr>
								<td>
								  <div id="calendar"></div>
								 </td>
						    </tr>
						</table>
					</td>
					<td class="text-center" width=30% rowspan="2">
						<table class="table">
							<caption><h3>예약 정보</h3></caption>
							<tr>
							<td class="text-center">
								<img: src="'http://www.menupan.com'+food_detail.poster">
							</td>	
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="text-center" width=45% height="200">
						<table class="table" v-show="isTime">
							<caption><h3>시간 정보</h3></caption>
						</table>
					</td>
					<td class="text-center" width=30% height="200">
						<table class="table" v-show="isInwon">
							<caption><h3>인원 정보</h3></caption>
					<tr>
						
					</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		let rApp=Vue.createApp({
			data(){
				return{
					isDay:false,
					isTime:false,
					isInwon:false,
					food_list:[],
					type:'한식',
					food_detail:{},
					rday:'',
					rtime:['10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00'],
					ts:'',
					inwon:['1명','2명','3명','4명','5명','6명','7명','8명','9명'],
					is:''
					
					
				  }
				},
				mounted(){
					let date=new Date()
					let year=date.getFullYear()
					let month=date.getMonth()+1 //("0"+(1+date.getMonth())).slice(-2);
					let day=date.getDate //("0"+date.getDate()).slice(-2)
					// 시작과 동시에 변수 초기화
					document.addEventListener('DOMContentLoaded', function(){
						let calendarEl=document.getElementById('calendar')
						let calendar=new FullCalendar.Calendar(calendarEl,{
							initialView:'dayGridMonth',
							height:450,
							headerToolbar:{
								left:'prev, next',
								center:'title'
							},
								validRange:{
									start:year+"-"+month+"-"+day
								}
								themeSystem:'bootstrap',
								editable:true,
								dropable:true,
								dateClick:((info)=>{
									//alert('click date:'+info.dateStr)
									
									
								})
						})
						calendar.render()
					})
					this.dataRecv()
				},
				methods:{
					inwonSelect(inwon){
						
					},
					timeSelect(time){
						this.isInwon=true
						this.ts=time
					},
				}
				
		}).mount('.container')
	</script>
</body>
</html>