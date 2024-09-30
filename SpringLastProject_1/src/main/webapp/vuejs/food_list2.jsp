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
.container-fluid{
   margin-top: 50px
}
.col-sm-8, .col-sm-4{
   margin: 0px auto;
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
	<div class="container-fluid">
	  <div class="col-sm-8">
	  	<div class="col-md-3" v-for="vo in list">
		    <div class="thumbnail">
		      <a href="nav-link" @click>
		        <img src="vo.poster" alt="Lights" style="width:230px; height:130px;">
		        <div class="caption">
          	<p>{{vo.name}}</p>
        </div>
      </a>
    </div>
	  </div>
	  <div class="col-sm-4" v-show="isShow">
	  <table class="table">
	  	<tr>
	  		<td width="30%"
	  	</tr>
	  </table>
	  </div>
	</div>
	<script>
	const food_detail={
			props:['detail'],
			template:`
			
			
			`
	}
		let app=Vue.createApp({
			data(){
				return{
					list:[],
					curpage:1,
					startPage:0,
					endPage:0,
					totalpage:0,
					detail:{},
					isShow:false
					
				}
			},
			mounted(){
				
			},
			updated(){
				
			},
			methods:methods:{
				detailData(fno){
					axios.get('../food/detail_vue.do',{
						
					})
				}
			}
				prev(){
					 this.curpage=this.startPage-1
	    			 this.dataRecv()
				},
				next(){
					 this.curpage=this.endPage+1
	    			 this.dataRecv()
				},
				pageChange(page){ 
					this.curpage=page
	    			this.dataRecv()
				},
				dataRecv(){
					axios.get('../food/list_vue.do',{
						params:{
						page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.list=response.data.list
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.startPage=response.data.startPage
						this.endPage=response.data.endPage
						
					}).catch(error=>{
						console.log(error.response)
					})
			},
			components:{
				
				 
			}
		}).mount('.container-fluid')
	</script>
</body>
</html>