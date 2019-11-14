<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th></th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="n" items="${CommunityBoard }">
				<tr>
					<td>${n.id}</td>
					<td rowspan="2">1*2 셀</td>
					<td rowspan="1">1*3 셀</td>
					<td>1*4 셀</td>
					<td rowspan="2">1*5 셀</td>
				</tr>
				<tr>
					<td>2*1 셀</td>
					<td colspan="2">2*3 셀</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>