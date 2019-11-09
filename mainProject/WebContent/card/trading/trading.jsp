<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trading/trading.css">

</head>
<body>
<div id="trading-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>기현 알고리즘</div>
			<div>204,000</div>
		</div>
	</header>
	
	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<input class="button button-chart" type="button" value="일봉">
		<input class="button button-chart" type="button" value="주봉">
		<input class="button button-chart" type="button" value="월봉">
		<div>차트</div>
	</section>
	
	<!-- --------------- page-bottom -------------- -->
		<section class="page-bottom">
		<div id="page-bottom-box">
			<div class="show-button-align">
				<input class="button button-status" type="button" value="자산 상황">
				<div>176,000</div>
				<input class="button button-status" type="button" value="구매 수량">
				<div>
					<input id="text">
				</div>
				<input class="button button-button" type="button" value="매       수">
			</div>
			<div class="show-button-align">
				<input class="button button-status" type="button" value="보유 상황">
				<div>0</div>	
				<input class="button button-status" type="button" value="매도 수량">
				<div>
					<input id="text">
				</div>
				<input class="button button-button" type="button" value="매       도">
			</div>
		</div>
	</section>
</div>	
</body>
</html>