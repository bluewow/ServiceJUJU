<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<c:set var ="n" value="${captureList}" />

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
			<table border=1>
				<thead>
					<tr>
						<td class="stoc">종목명</td>
						<td class="title">제목</td>
						<td class="capturedate">캡쳐날짜</td>
					</tr>
				</thead>
				<%--- <tbody>
					<c:forEach var="n" items="${captureList}">
						<tr>
							<td>${n.MEMO}</td>
							<td>${n.title}</td>
							<td>${n.regdate}</td>
						</tr>
					</c:forEach>
				</tbody>--%>
				<tbody>
					<tr>
					<td class="stoc">test</td>
					<td class="title">test</td>
					<td class="capturedate">19/11/27</td>
					</tr>
					<tr>
					<td class="stoc">test</td>
					<td class="title">test</td>
					<td class="capturedate">19/11/27</td>
					</tr>
				</tbody>
				</div>
			</main>
				</table>
		</div>
	</main>
</body>
</html>