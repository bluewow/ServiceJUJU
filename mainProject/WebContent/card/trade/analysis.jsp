<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

<!--d3js CDN-->
<script src="https://d3js.org/d3.v4.min.js"></script>
<!--다운 받아서 포함 시키기-->
<link rel="stylesheet" href="../../css/billboard.css">
<script src = "../../js/billboard.js"></script>
    
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/analysis.css">
<script type="text/javascript" src="../../js/trade/analysis.js"></script>

</head>
<body>
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div></div><!-- 종목명 -->
			<div>204,000</div>
		</div>
<%-- 		<c:if test="${not empty sessionScope.id }">
			<input id="capture" type="button" value="캡쳐하기">
		</c:if> --%>
	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div>
			<div>
				<br>
				<div id="chartA"></div>
				<br>
	 			<div id="chartB"></div>
			</div>
			<div>
				<br>
				<div id="chartC"></div>
				<br>
				<div id="chartD"></div>
			</div> 
	    </div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
		<div id="chartE"></div>
	</section>
	
</div>
</body>
</html>