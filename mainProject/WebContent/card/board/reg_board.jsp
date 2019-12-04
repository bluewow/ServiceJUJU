<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="kr">

<head>
<meta charset="UTF-8">

<title>글쓰기</title>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">

<link href="../../css/board/reg_board.css" type="text/css"
	rel="stylesheet">
<!-- <script src="../../js/board/reg_board.js"></script> -->
</head>
<body>
	<div id=frame>
		<div id=title-row>
			<input type=text class="title" maxlength="20" placeholder="제목을 입력하시오">
			
		</div>
		<div id=content-row>
			<textarea class="content" maxlength="400" placeholder="내용을 입력하시오"></textarea>
			<input type="button" class="cancel" name="cancel" value="취소">
			<input type="button" class="reg" name="reg" value="확인">
		</div>
	</div>
</body>
</html>