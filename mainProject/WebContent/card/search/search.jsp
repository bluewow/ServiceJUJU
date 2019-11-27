<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<c:set var ="n" value="${search}" />

<html>
<head>

<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/search/search.css">
</head>
<!-- style="overflow:scroll" -->
<body>
	<div id ="bodyScroll">
	<section id="search-form">
		<h1 class="d-none">주식회사검색</h1>
		<form action ="search" method="get">
			<div id ="search-div">
				<input id ="search-text" type="text" name="search">
				<button class="search-button"></button>
			</div>
		</form>
	</section>
		
	<section id="recommendKeyword">
		<ul>
			<li id="recommendKeyword_fixed">추천검색어 </li>
			
			<c:forEach var="r" items="${recommendKeyword}">
				<a href="search?search=${r}"><span>${r}</span></a>
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
			
			<tbody>
			
			<%-- <c:forEach var="n" items="${search}"> --%>
				<tr>
					<td>1</td>
					<td>${n.companyName}<a href="${n.website}" target="_blank"><img src="/css/search/link.png" alt=""></a></td>
					<td>${n.stockItemName}</td>
					<td><img src="/css/search/interest_no.png" alt=""></td>
				</tr>
			<%-- </c:forEach> --%>
			</tbody>
			
			<%-- 	<tr>
					<th>1</th>
					<td><a href=""></a>${n.companyName}<img src="/css/search/link.png" alt=""></td>
					<td>${n.stockItemName}</td>
					<td><img src="/css/search/interest_no.png" alt="">${n.website}</td>
				</tr> --%>
			
			
			<!-- <tbody>
				<tr>
					<td>1</td>
					<td>광동 사운드 <img src="/css/search/link.png" alt=""></td>
					<td>기술,하드웨어</td>
					<td><img src="/css/search/interest_no.png" alt=""></td>
				</tr>
			</tbody> -->
			
			<tbody>
				<tr>
					<td>2</td>
					<td>테스트</td>
					<td>미디어</td>
					<td><img src="/css/search/interest_yes.png" alt=""></td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>3</td>
					<td>테스트</td>
					<td>은행</td>
					<td>관심x</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>4</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>5</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>6</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>7</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>8</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td>9</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
					
			
			<tbody>
				<tr>
					<td>10</td>
					<td>테스트</td>
					<td>미디어</td>
					<td>관심</td>
				</tr>
			</tbody>
			
		</table>
	</section>
	</div>
</body>
</html>