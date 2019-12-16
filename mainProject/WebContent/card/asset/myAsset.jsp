<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>stock market</title>

	<!-- Layout Styles -->

	<link rel="stylesheet" type="text/css" href="../../css/myAsset.css">
	<script src="../../js/asset/myAsset.js"></script>
</head>

<body class="scrollbar custom-scrollbar-style">
	<main id="card">
		<section id="card-top">
			<div id="titleAssetTrend" class="wrap">
				<div class="title">
					<h1>자산 추이</h1>
				</div>
				<div>
					<h2>현 보유 자산</h2>
				</div>
				<div>
					<div class="my-asset-info wrap">
						<ul class="buttons">
							<li class="selected-button"><a href="">일봉</a></li>
							<li><a href="">주봉</a></li>
							<li><a href="">월봉</a></li>
						</ul>
					</div>
					<p><fmt:formatNumber value="${assetPesent}"/></p>
				</div>
			</div>
			<div id="assetTrend">
				<div id="graph-assetTrend">
					<h1>선형 그래프</h1>
				</div>

				<div id="setPeriodgraph-assetTrend">
					<h1>기간 지정 그래프</h1>
				</div>
			</div>
		</section>
		<section id="card-bottom">
			<div class="title">
				<h1>자산 분포도</h1>
			</div>
			<div id="assetDistribution">
				<div id="graph-assetDistr">
					<h2 class="visable-none">원형그래프</h2>
					<div class="graph"><img src="../../images/distr_graph_sample.png"></div>
				</div>

				<div id="list-stock">
						<h2 class="visable-none">종목 리스트</h2>
						<div class="list">	
							<table>
								<tr>
									<td><img src="../../images/distr_list_01.png"></td>
									<td>광동사운드</td>
								</tr>
								<tr>
									<td><img src="../../images/distr_list_02.png"></td>
									<td>네오위즈</td>
								</tr>
								<tr>
									<td><img src="../../images/distr_list_03.png"></td>
									<td>네오위즈홀딩스</td>
								</tr>
								<tr>
									<td><img src="../../images/distr_list_04.png"></td>
									<td>디지캡</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<template class="template-list-stock">
					<tr>
						<td>
							<img src="">
						</td>
						<td>
							stockName
						</td>
					</tr>


				</template>
		</section>
	</main>
</body>

</html>