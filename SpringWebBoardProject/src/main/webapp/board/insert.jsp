<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0
$(function(){
	$('#addBtn').on('click',function(){
		$('file-tbody').append(
			'<tr id="f"+fileIndex+'">'		
		
		)
	})
})

</script>
</head>
<body>

</body>
</html>