<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/trading.css">
<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../../js/trade/trade.js"></script>
	 
	 
</head>
<body class="scrollbar custom-scrollbar-style">
<div id="trading-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div></div>		<!-- 종목명 -->
			<div></div>		<!-- 현재가 -->
			<span></span> 	<!-- up/down 기호-->
			<span></span>	<!-- 원 -->
			<span></span>	<!-- % -->
		</div>
	</header>
	<!-- TODO 장이마감되었습니다 -->
	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
<!-- 		
		<form action="trade" method="get">
			<input class="button button-chart day_" type="submit" name="date" value="일봉">
			<input class="button button-chart" type="submit" name="date" value="주봉">
			<input class="button button-chart" type="submit" name="date" value="월봉">
		</form> 
-->
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
					<!-- enter 입력을 막음 -->
					<input class="data" id="text" type="text" name="Purse/Sold" autocomplete="off" onkeydown="if(event.keyCode == 13) return false;"> 
				</div>
				<input id="sell" class="event button button-button animation" type="button" name="trade" value="매       도">
				<%-- <input class="event button button-button animation ${shadow }" type="submit" ${disable } name="trade" value="매       도"> --%>
			</div>
		</form>
	</section>
</div>	


</body>
</html>