<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->

<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- progress bar -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/analysis.css">

</head>
<body>
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>기현 알고리즘</div>
			<div>204,000</div>
		</div>
		<input id="capture" type="button" value="캡쳐하기">
	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div class="page-left-chart">차트1</div>
		<div class="page-right-chart">
			<div class="page-right-write">급등 (24%)</div>
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-red" style="width: 40%;">40%</div>
	        </div>
	        <div class="page-right-write">우상향 (36%)</div>
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-red" style="width: 40%;">40%</div>
	        </div>	      
	        <div class="page-right-write">우하향 (25%)</div>  
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-red" style="width: 40%;">40%</div>
	        </div>
	        <div class="page-right-write">급락 (25%)</div>
	        <div class="progress progress-right-chart">
	            <div class="progress-bar progress-red" style="width: 80%;">40%</div>
	        </div>	        
		</div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
		<div id="comment">분석 내용 </div>
		<div id="chart-bottom">
	        <div class="content margin-content">
	        	<div class="content-first">관심도<i class="fa fa-question-circle fa-lg ho" aria-hidden="true"></i></div> 
	        	<div class="progress content-second">
		            <div class="progress-bar progress-red" style="width: 40%;">40%</div>
		        </div>
	     	   <div class="content-third"><i class="fa fa-arrow-up" aria-hidden="true"></i></div>
	        </div>
	        <div class="content">
				<div class="content-first">재무상황<i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></div>       	
		        <div class="progress content-second">
		            <div class="progress-bar progress-red" style="width: 50%">50%</div>
		        </div>
		        <div class="content-third"><i class="fa fa-arrow-down" aria-hidden="true"></i></div>  
	        </div>
	        <div class="content">
	        	<div class="content-first">미정<i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></div>
	        	 <div class="progress content-second">
		            <div class="progress-bar progress-blue" style="width: 60%">60%</div>
		        </div>		     
	        	<div class="content-third"><i class="fa fa-arrow-down" aria-hidden="true"></i></div>
	        </div>
		</div>
	</section>
	
</div>
</body>
</html>