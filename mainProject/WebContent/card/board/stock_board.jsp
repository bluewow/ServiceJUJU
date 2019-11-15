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
			<c:forEach var="n" items="${CommunityBoard }">
				<tr>
					<td>${n.id}</td>
					<td rowspan="2">${n.title}</td>
					<td rowspan="1">${n.regdate}</td>
					<td>${n.hit}</td>
					<td rowspan="2"><input type="button" name="Favorite" value="Favorite"></td>
				</tr>
				<tr>
					<td><input type="button" name="Del"  value="Del"></td>
					<td colspan="2">${n.writerId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>