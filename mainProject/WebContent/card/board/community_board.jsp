<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="kr">

<head>
<meta charset="UTF-8">

<title>Community</title>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css"
	href="../../css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

<link href="../../css/board/community_board.css" type="text/css"
	rel="stylesheet">


</head>
<body>
	<div id="communityScroll">
		<div>
			<input type="button" name="my_board_view" value="My"> <input
				type="button" name="favo_board_view" value="관심">
		</div>
		<br>
		<table border=1>
			<thead align="center">
				<tr>
					<th></th>
					<th width="200">제목</th>
					<th>작성일</th>
					<th>조회</th>
					<th></th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="n" items="${CommunityBoard}">
					<tr>
						<td>${n.id}</td>
						<td rowspan="2">[${n.stockName}] ${n.title}</td>
						<td rowspan="1"><fmt:formatDate value="${n.regdate}"
								pattern="yy/MM/dd hh:mm" /></td>
						<td>${n.hit}</td>
						<td rowspan="2"><input type="button" name="Favo" value="Favo"></td>
					</tr>
					<tr>
						<td><input type="button" name="del" value="del"></td>
						<td colspan="2">${n.writerId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>