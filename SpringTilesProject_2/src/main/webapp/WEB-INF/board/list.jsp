<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
 <div class="container">
     <h3 class="text-center">Front 게시판</h3>
     <div class="row">
      <table class="table">
       <tr>
        <td>
         <a href="../board/insert.do" class="btn btn-sm btn-primary">새글</a>
        </td>
       </tr>
      </table>
      <%--
      		1. 검색 => 다이얼로그
      		2. 댓글
      
       --%>
      <table class="table table-hover">
       <tr>
        <th width=10% class="text-center">번호</th>
        <th width=45% class="text-center">제목</th>
        <th width=15% class="text-center">이름</th>
        <th width=20% class="text-center">작성일</th>
        <th width=10% class="text-center">조회수</th>
       </tr>
        <tr v-for="vo in board_list">
            <td width=10% class="text-center" v-text="vo.no"></td>
	        <td width=45% v-text="vo.subject"></td>
	        <td width=15% class="text-center" v-text="vo.name"></td>
	        <td width=20% class="text-center" v-text="vo.dbday"></td>
	        <td width=10% class="text-center" v-text="vo.hit"></td>
         </tr>
      </table>
      </div>
      <script>
      	let boardApp=Vue.createApp({
      		data(){
      			return{
      				board_all:{},
      				board_list:[],
      				curpage:1,
      				totalpage:0
      			}
      		},     		
      		mounted(){
      			//$(function(){}) window.onload
      			axios.get('../board/list_vue.do',{
      				params:{
      					page:this.curpage
      				}
      			}).then(response=>{
      				console.log(response.data)
      				this.board_all=response.data
      				this.board_list=response.data.list
      			})
      		}
      		
      	     }
      	}).mount('#boardApp')
      </script>
      <table class="table">
        <tr>
          <td class="text-center">
           <a href="#" class="btn btn-sm btn-danger">이전</a>
             ${curpage } page / ${totalpage } pages
           <a href="#" class="btn btn-sm btn-danger">다음</a>
          </td>
        </tr>
      </table>
     </div>
</body>
</html>