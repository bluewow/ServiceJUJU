<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="n" value="${captureList}" />

<html>
<head>

<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/captureMemo.css">
</head>
<!-- style="overflow:scroll" -->
<body>
	<main id="contaner">
		<div class="capture-table">
			<table class="table">
				<thead>
					<tr>
						<td class="stoc">종목명</td>
						<td class="title">제목</td>
						<td class="capturedate">캡쳐날짜</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${captureList}">
						<tr>
							<td class="stoc">${n.MEMO}</td>
							<td  class="title">${n.title}</td>
							<td class="capturedata">${n.regdate}</td>
							<td class="button"><input type="button" id="button" name="delbutton" value="del"></td>
						</tr>
					</c:forEach>
				</tbody>

				</div>
				</main>
			</table>
		</div>
	</main>
</body>
</html>