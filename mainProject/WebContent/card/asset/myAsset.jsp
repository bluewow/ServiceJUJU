<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	html>

	<head>
		<meta charset="UTF-8">
		<title>stock market</title>
	
		<!-- Layout Styles -->
	
		<link rel="stylesheet" type="text/css" href="../../css/myAsset.css">
	
	</head>
	
	<body>
		<main id="card">
			<section>
				<div id="titleAssetTrend" class="wrap">
					<div class="asset-graph-title">
						<h1>자산 추이</h1>
					</div>
					<div>
						<h2>현 보유 자산</h1>
					</div>
					<div>
						<div class="my-asset-info wrap">
							<ul class="buttons">
								<li><a href="">일봉</a></li>
								<li><a href="">주봉</a></li>
								<li><a href="">월봉</a></li>
							</ul>
						</div>
						<p>21,176,000</p>
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
			<section>
				<div class="asset-graph-title">
					<h1>자산 분포도</h1>
				</div>
				<div id="asset-distribution">
					<div id="graph-assetDistr">
						<h2>원 그래프</h2>
					</div>
	
					<div id="list-stock">
						<h2 class="visable-none">종목 리스트</h2>
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
					</div>
				</div>
			</section>
		</main>
	</body>
	
	</html>