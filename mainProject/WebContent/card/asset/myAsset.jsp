<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>stock market</title>

<!-- Layout Styles -->

<link rel="stylesheet" type="text/css" href="../../css/myAsset.css">

</head>
<body>
	<!-- =====header1=====  -->
	<div id="card1">
		<header id="titleAssetTrend">
			<h1>자산 추이</h1>

			<section>
				<h1 class="d-none">헤더</h1>
				<nav id="menuPole">
					<h1 class="d-none">봉 메뉴</h1>
					<ul>
						<li><a href="">일봉</a></li>
						<li><a href="">주봉</a></li>
						<li><a href="">월봉</a></li>
					</ul>
				</nav>

				<section id="asset-present">
					<h1>현 보유 자산</h1>
					<p>21,176,000</p>
				</section>

			</section>
		</header>
		<!-- =====body1=====  -->
		<div id="body1">
			<main>
			<section>
				<h1 class="d-none">자산추이 그래프</h1>

				<section id="graph-assetTrend">
					<h1>선형 그래프</h1>
				</section>

				<section id="setPeriodgraph-assetTrend">
					<h1>기간 지정 그래프</h1>
				</section>
			</section>
			</main>
		</div>
	</div>
	<!-- =====header2=====  -->
	<div id="card2">
		<header id="assetDistr">
			<h1>자산 분포도</h1>
		</header>
		<!-- =====body2=====  -->
		<div id="body2-myAsset">
			<main>
			<section>
				<h1 class="hidden">자산분포도 그래프</h1>
				
				<section id="graph-assetDistr">
					<h1>원 그래프</h1>
				</section>

				<section id="list-stock">
					<h1 class="d-none">종목 리스트</h1>
						<table>
							<tr>
								<td>이미지</td>
								<td>광동사운드</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>네오위즈</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>네오위즈홀딩스</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>디지캡</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>코덱스</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>KODEX 코스닥 150 레버리지</td>
							</tr>
							<tr>
								<td>이미지</td>
								<td>기타</td>
							</tr>
						</table>
				</section>
			</section>
			</main>
		</div>
	</div>

</body>
</html>