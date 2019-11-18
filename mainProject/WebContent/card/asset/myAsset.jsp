<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>stock market</title>

<!-- Layout Styles -->
<link rel="stylesheet" type="text/css" href="../../css/layout.css">
<link rel="stylesheet" type="text/css" href="../../css/myAsset.css">

</head>
<body>
	<!-- =====header1=====  -->
	<div id="assetTrend">
		<header id="header1">
			<div>
			<h1>자산 추이</h1>
			</div>

			<section>
				<h1 class="hidden">헤더</h1>
		
				<nav id="listPole">
					<div>
					<h1 class="hidden">봉 버튼 리스트</h1>
					<form>
						<button type="submit">일봉</button>
						<button type="submit">주봉</button>
						<button type="submit">월봉</button>
					</form>
					</div>
				</nav>

				<section id="assetPresent">
					<h1>현 보유 자산</h1>
					<div>21,176,000</div>
				</section>

			</section>
		</header>
		<!-- =====body1=====  -->
		<div id="body1">
			<main>
			<section>

				<h1 class="hidden">자산추이 그래프</h1>
				<section id="graph-assetTrend">
					<h1 class="hidden">선형 그래프</h1>
					<div></div>
				</section>

				<section id="setPeriodgraph-assetTrend">
					<h1 class="hidden">기간 지정 그래프</h1>
					<div></div>
				</section>

			</section>
			</main>
		</div>
	</div>
	<!-- =====header2=====  -->
	<div id="assetDistr">
		<header id="header2-myAsset">
			<h1>자산 분포도</h1>
			<section>
				<h1 class="hidden">헤더</h1>
			</section>
		</header>
		<!-- =====body2=====  -->
		<div id="body2-myAsset">

			<main class="main">
			<section>

				<h1 class="hidden">자산분포도 그래프</h1>
				<section id="graph-assetDistr">
					<h1 class="hidden">원 그래프</h1>
					<div></div>
				</section>

				<section id="list-stock">
					<h1 class="hidden">종목 리스트표</h1>
					<div>
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
				</section>

			</section>
			</main>
		</div>
	</div>

</body>
</html>