<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>

<head>
<meta charset="utf-8">
<title>stock market</title>

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">
<!-- Bootstrap Styles -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="./css/normalize.css">

<!-- Milligram CSS minified -->
<link rel="stylesheet" type="text/css" href="./css/milligram.min.css">

<!-- Main Styles -->
<link rel="stylesheet" type="text/css" href="./css/main.css">




<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>

<body>
	<!-- =============================================================================================================== -->
	<header class="navbar">
		<h1 style="display: none;">Stock Market</h1>
		<section class="row">
			<h1 style="display: none;">hedaer</h1>
			<section class="column column-30 col-site-title">
				<h1 style="display: none;">site title</h1>
				<a href="#" class="site-title float-left">Stock Market</a>
				<section class="column column-30">
					<h1 style="display: none;">user section</h1>
					<div class="user-section">
						<a href="#"> <img src="http://via.placeholder.com/50x50"
							alt="profile photo" class="circle float-left profile-photo"
							width="50" height="auto">
							<div class="username">
								<h4>bluewow</h4>
								<p>Administrator</p>
							</div>
						</a>
					</div>
				</section>
			</section>
			<!-- <div class="column column-40 col-search"><a href="#" class="search-btn fa fa-search"></a>
                <input type="text" name="" value="" placeholder="Search..." />
            </div> -->

		</section>
	</header>
	<!-- =============================================================================================================== -->
	<main class="row">
	<h1 style="display: none;">cards</h1>
	<section id="main-content" class="column">
		<!-- 첫번째 로우 -->
		<div class="row grid-responsive">
			<!-- 검색카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block">
					   <div style="border: 1px solid hidden; border-radius: 50%">
						<ul class="nav nav-tabs" id="myTab" role="tablist style=">
							<li class="nav-item"><a class="nav-link active"
								id="index1-tab" data-toggle="tab" href="#index1" role="tab"
								aria-controls="index1" aria-selected="true">뉴스&이슈</a></li>
							<li class="nav-item"><a class="nav-link" id="index2-tab"
								data-toggle="tab" href="#index2" role="tab"
								aria-controls="index2" aria-selected="false">전체 커뮤니티</a></li>
							<li class="nav-item"><a class="nav-link" id="index3-tab"
								data-toggle="tab" href="#index3" role="tab"
								aria-controls="index3" aria-selected="false">종목별 커뮤니티</a></li>
						</ul>
						</div>
						<div class="tab-content" id="myTabContent"
							style="background: white; height: 93%">
							<div class="tab-pane fade show active" id="index1"
								role="tabpanel" aria-labelledby="index1-tab"></div>
							<div class="tab-pane fade" id="profile" role="index2"
								aria-labelledby="index2-tab"></div>
							<div class="tab-pane fade" id="contact" role="index3"
								aria-labelledby="index3-tab"></div>
						</div>
					</div>
				</div>
			</section>

			<!-- 분석 매매 예측 카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block">
						<iframe height="500" src="" scrolling="no"></iframe>
					</div>
				</div>
			</section>

			<!-- 주식 캡처 카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block">
						<iframe height="500" src="" scrolling="no"></iframe>
					</div>
				</div>
			</section>
		</div>

		<!-- 두번째 로우 -->
		<div class="row grid-responsive">
			<!-- 커뮤니티 카드 -->
			<section class="column column-33">
				<div class="card ">
					<div class="card-block"></div>
				</div>
			</section>

			<!-- 구매 / 관심 목록 카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block"></div>
				</div>
			</section>

			<!-- 자산추이 카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block"></div>
				</div>
			</section>
		</div>

		<!-- 세번째 로우 -->
		<div class="row grid-responsive">
			<!-- 빈 공간 -->
			<div class="column column-33"></div>

			<!-- 빈 공간 -->
			<div class="column column-33"></div>

			<!-- 랭킹 카드 -->
			<section class="column column-33">
				<div class="card">
					<div class="card-block"></div>
				</div>
			</section>
		</div>
	</section>
	</main>
	<!-- =============================================================================================================== -->
	<footer>
		<section></section>
	</footer>
	<!-- =============================================================================================================== -->

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>

</html>