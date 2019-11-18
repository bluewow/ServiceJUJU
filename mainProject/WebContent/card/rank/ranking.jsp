<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="kr">

<head>
	<title>ranking</title>
	
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700" rel="stylesheet">
	
	<!-- Template Styles -->
	<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">

	<!-- CSS Reset -->
	<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

	<link href="../../css/rank.css" type="text/css" rel="stylesheet">
</head>
<!-- ======================================================================= -->
<body>
	<main id="contaner">
		<table id="rankTable">
			<thead class="rankInfo">
				<tr>
					<td class="rank">순위</td>
					<td class="profileImg"></td>
					<td class="name">아이디</td>
					<td class="assets">보유 자산</td>
					<td class="assetsGrowth">자산 상승율</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ranker" items="${rankers}" varStatus="status">
					<tr>
						<td class="rank">${status.count}</td>
						<td class="profileImg"></td>
						<td class="name">${ranker.nickName}</td>
						<td class="assets"><fmt:formatNumber value="${ranker.vMoney}" pattern="#,###"/></td>
						<td class="assetsGrowth"><fmt:formatNumber value="${ranker.vMoney/10000}" pattern="#,###"/>%</td>
					</tr>
				</c:forEach>
			</tbody>

			<tbody id="myRank">
				<tr>
					<td class="rank">121</td>
					<td class="profileImg"></td>
					<td class="name">기현천재</td>
					<td class="assets">21,176,000</td>
					<td class="assetsGrowth">33,15%</td>
				</tr>
			</tbody>
		</table>
	</main>
</body>

</html>