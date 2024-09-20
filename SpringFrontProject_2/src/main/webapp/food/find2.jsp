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
<input type= text size=20 class="input-sm" ref="address" v-model="address"
	@key  >
</div>
</div>
<script>
	let findApp=Vue.createApp({
		data(){
			return{
				find_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0,
				address:'마포'
			}
		},
		mounted(){
			this.dataRecv()
		},
		methods:{
			find(){
				if(this.address==="")
			}
			dataRecv(){
				axios.get("http://localhost:8080/web/food/find_vue.do",{
					params:{
						page:this.curpage,
						address: this.address
					}
				}).then(response=> {
					this.find_list=response.data.list
					this.curpage=response.data.curpage
					this.totalpage = response.data.totalPage,
					this.startPage:0,
					this.endPage:0,
					this.address:'마포'
				})
			}
		}
	}).mount('.container')

</script>
</body>
</html>