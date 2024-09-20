<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/socketjs-client/1.4.0/sockjs.min.js"></script>
<script type="text/javascript">
function connection()
{
	name=$('#name').val()
	if
	
}
function onMessage(event)
{
	let data=event.data;
	if(data.substring(0,4)==="msg:")// 
	{
		appendMessage(data.substring(4))
	}
}
function appendMessage(msg)
{
	$('#recvMsg').append(msg+"<br>")	
}
$(function(){
	$('#inputBtn').click(function(){
		connection();
	})	
	$('#outputBtn').click(function(){
		websocket.close();	
	})
	$('#sendBtn').click(function(){
		send()
	})
	$('#sendMsg').keydown(function(){
		
		
	})
	}

</script>
<style type="text/css">
.container{
	margin-top: 50px
}
.row{
	margin: 0px auto;
	width: 600px
}
#chatArea{
	
	
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">실시간 접속자 채팅</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<input type = text id="name" size="20" class="input-sm">
						<input type=button value="입장" class="btn btn-sm btn-primary" id="inputBtn">
						<input type=button value="퇴장" class="btn btn-sm btn-primary" id="outputBtn">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>