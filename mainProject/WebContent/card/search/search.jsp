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
	<section id="search-form">
		<h1 class="d-none">주식회사검색</h1>
		<form action ="search" method="get">
			<div>
				<input id ="search-text" type="text" name="search">
				<button>검색</button>
			</div>
		</form>
	</section>
		
	<section id="recommendKeyword">
		<!-- <h1>추천 검색어</h1> -->
		<ul>
			<li id="recommendKeyword_fixed">추천검색어</li>
			<li>광동 사운드</li>
			<li>기현 알고리즘</li>
			<li>나람 금융</li>
			<li>명훈 소프트</li>
		</ul>
	</section>
	
	<section >
		<h1 class="d-none">주식회사 검색 결과</h1>
		<table>
			<thead>
				<tr>
					<td>번호</td>
					<td>종목명</td>
					<td>산업분류</td>
					<td>홈페이지</td>
					<td>관심유무?</td>
				</tr>
			</thead>
			
			<%-- <tbody>
			(아래) 나중에 사용할 코드
			<c:forEach var="n" items="${search}">
				<tr>
					<th>1</th>
					<td><a href=""></a>${n.companyName}</td>
					<td>${n.stockItemName}</td>
					<td>${n.website}</td>
				</tr>
			</c:forEach>
			</tbody> --%>
			
			<tbody>
				<tr>
					<th>1</th>
					<td><a href="">광동 사운드</a></td>
					<td>기술,하드웨어</td>
					<td>홈페이지</td>
					<td>관심O</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>2</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심x</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>3</th>
					<td><a href="">기업 은행</a></td>
					<td>은행</td>
					<td>홈페이지</td>
					<td>관심x</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>4</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>5</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>6</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>7</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>8</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>9</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>2</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>홈페이지</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<th>10</th>
					<td><a href="">경인 일보</a></td>
					<td>미디어</td>
					<td>관심</td>
					
				</tr>
			</tbody>
			
			
		</table>
	</section>
	
</body>
</html>