<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trading/analysis.css">


<!-- progress bar -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>기현 알고리즘</div>
			<div>204,000</div>
		</div>
	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div class="page-left-chart">차트1</div>
		<div class="page-right-chart">
			급등, 우상향, 우하향, 급락
		</div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
		<div id="comment">분석 내용</div>
		<div id="chart-bottom"></div>
		
	</section>
	
	<div class="container">
		<div class="row">                   
	        <div class="progress">
	            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
	                <span class="sr-only">60% Complete</span>
	            </div>
	            <span class="progress-type">HTML / HTML5</span>
	            <span class="progress-completed">60%</span>
	        </div>
	        <div class="progress">
	            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
	                <span class="sr-only">40% Complete (success)</span>
	            </div>
	            <span class="progress-type">ASP.Net</span>
	            <span class="progress-completed">40%</span>
	        </div>
	        <div class="progress">
	            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
	                <span class="sr-only">20% Complete (info)</span>
	            </div>
	            <span class="progress-type">Java</span>
	            <span class="progress-completed">20%</span>
	        </div>
	        <div class="progress">
	            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
	                <span class="sr-only">60% Complete (warning)</span>
	            </div>
	            <span class="progress-type">JavaScript / jQuery</span>
	            <span class="progress-completed">60%</span>
	        </div>
	        <div class="progress">
	            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
	                <span class="sr-only">80% Complete (danger)</span>
	            </div>
	            <span class="progress-type">CSS / CSS3</span>
	            <span class="progress-completed">80%</span>
	        </div>
		</div>
	</div>	
</div>
</body>
</html>