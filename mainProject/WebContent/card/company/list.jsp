<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<c:set var ="n" value="${search}"/>

<html>
<head>

<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/search/search.css">
<script src="../../js/search/search.js"></script>
</head>
<!-- style="overflow:scroll" -->
<body>
	<div id ="bodyScroll">
	<section id="search-form">
		<h1 class="d-none">주식회사검색</h1>
		<form action ="list" method="get">
			<div id ="search-div">
				<!-- <label for= "search-text"> -->
					<input id ="search-text" type="text" name="companyName">
					<button class="search-button"></button>
				<!-- </label> -->
			</div>
		</form>
	</section>
		
	<section id="recommendKeyword">
		<ul>
			<li id="recommendKeyword_fixed">추천검색어 </li>

			<c:forEach var="r" items="${recommendKeyword}">
				<a href="list?companyName=${r}"><span>${r}</span></a>
			</c:forEach>
			
		</ul>
	</section>
	
	<section id ="search-result">
		<h1 class="d-none">주식회사 검색 결과</h1>
		<table>
			<thead>
				<tr>
					<td>번호</td>
					<td>종목명</td>
					<td>산업분류</td>
					<td>관심</td>
				</tr>
			</thead>
			
			<%-- <c:choose> --%>
			<%-- <tbody>
				<c:forEach var="sector" items="${sectorList}" varStatus="status" >
					<tr>
						<td>${status.count}</td>
						<td id="companyName">${sector.companyName}<a href="${sector.website}" target="_blank"><img src="/css/search/link.png" alt=""></a></td>
						<td>${sector.stockItemName}</td>
						<td class="attention"><img src="/css/search/interest_no.png" ></td>
					</tr>
				</c:forEach>
			</tbody> --%>
			
			
			
				<tbody>
					<c:forEach var="sector" items="${sectorList}" varStatus="status" >
						<tr>
							<td>${status.count}</td>
							<td>${sector.companyName}<a href="${sector.website}" target="_blank"><img src="/css/search/link.png" alt=""></a></td>
							<td>${sector.stockItemName}</td>
							<td class="attention"><img src="/css/search/interest_no.png" ></td>
						</tr>
					</c:forEach>
				</tbody>
			
			
				<%-- <tbody>
					<tr>
						<td>1</td>
						<td>${n.companyName}<a href="${n.website}" target="_blank"><img src="/css/search/link.png" alt=""></a></td>
						<td>${n.stockItemName}</td>
						<td class="attention"><img src="/css/search/interest_no.png"></td>
					</tr>
				</tbody> --%>
			
			
				
			
			
			
		</table>
	</section>
	</div>
</body>
</html>