<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- 매도버튼 음영처리 및 disable 처리 -->
<c:set var="shadow" value=""/>
<c:if test= "${myQuantity <= 0 }">
	<c:set var="shadow" value="shadow"/>
	<c:set var="disable" value="disabled"/>
</c:if>
		
<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/trading.css">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../../js/trade/trade.js"></script>
	 
	 
</head>
<body>
<div id="trading-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>${companyName }</div>
			<div>204,000</div>
		</div>
	</header>
	<!-- TODO 장이마감되었습니다 -->
	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<form action="trade" method="get">
			<input class="button button-chart day_" type="submit" name="date" value="일봉">
			<input class="button button-chart" type="submit" name="date" value="주봉">
			<input class="button button-chart" type="submit" name="date" value="월봉">
		</form>
		<div id="chart_div"></div>
	</section>
	
	<!-- --------------- page-bottom -------------- -->
		<section class="page-bottom">
		<form id="page-bottom-box" action="trade" method="get">
			<div class="show-button-align">
				<input class="button button-status" type="button" value="평균 단가">
				<div class="data"><fmt:formatNumber value="${aveAssets}" pattern="#,###" />원</div>
				<input class="button button-status" type="button" value="보유 수량">
				<div class="data"><fmt:formatNumber value="${myQuantity }" pattern="#,###" />주</div>	
				<input class="event button button-button animation" type="button" name="trade" value="매       수">

			</div>
			<div class="show-button-align">
				<input class="button button-status" type="button" value="가상 머니">
				<div class="data"><fmt:formatNumber value="${myAssets }" pattern="#,###" />원</div>
				<input class="button button-status" type="button" value="구매/매도">
				<div>
					<input class="data" id="text" type="text" name="Purse/Sold" autocomplete="off">
				</div>
				<input class="event button button-button animation" type="button" ${disable } name="trade" value="매       도">
				<%-- <input class="event button button-button animation ${shadow }" type="submit" ${disable } name="trade" value="매       도"> --%>
			</div>
		</form>
	</section>
</div>	


</body>
</html>