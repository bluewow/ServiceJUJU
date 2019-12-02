<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 매도버튼 음영처리 및 disable 처리 -->
<c:set var="shadow" value=""/>
<c:if test= "${myQuantity <= 0 }">
	<c:set var="shadow" value="shadow"/>
	<c:set var="disable" value="disabled"/>
</c:if>

<!-- 일봉/주봉/월봉 Check 처리 -->
<c:if test= "${day == 'on'}">
	<c:set var="day_" value="day_"/>
</c:if>
<c:if test= "${week == 'on'}">
	<c:set var="week_" value="week_"/>
</c:if>
<c:if test= "${month == 'on'}">
	<c:set var="month_" value="month_"/>
</c:if>
					
<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/trading.css">


<!--  팝업창 띄우기
 구글 Line + Control Chart -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  
</head>
<body>
<div id="trading-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>${companyName }</div>
			<div>204,000</div>
		</div>
	</header>
	<!-- TODO 장이마감되었습니다 -->
	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<form action="trade" method="get">
			<input class="button button-chart ${day_ }" type="submit" name="date" value="일봉">
			<input class="button button-chart ${week_ }" type="submit" name="date" value="주봉">
			<input class="button button-chart ${month_ }" type="submit" name="date" value="월봉">
		</form>
		<div id="chart_div"></div>
	</section>
	
	<!-- --------------- page-bottom -------------- -->
		<section class="page-bottom">
		<form id="page-bottom-box" action="trade" method="get">
			<div class="show-button-align">
				<input class="button button-status" type="button" value="보유 자산">
				<div><fmt:formatNumber value="${myAssets }" pattern="#,###" />원</div>
				<input class="button button-status" type="button" value="보유 수량">
				<div>${myQuantity }</div>	
				<input class="button button-button" type="submit" name="trade" value="매       수">

			</div>
			<div class="show-button-align">
				<input class="button button-status" type="button" value="평균 매수">
				<div><fmt:formatNumber value="${aveAssets }" pattern="#,###" />원</div>
				<input class="button button-status" type="button" value="구매/매도">
				<div>
					<input id="text" type="text" name="Purse/Sold" autocomplete="off">
				</div>
				<input class="button button-button ${shadow }" type="submit" ${disable } name="trade" value="매       도">
			</div>
		</form>
	</section>
</div>	

<script>


/*
	visualization 
	 - Set Data
	 - Set Option
	 - Draw LineChart

*/

google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {
	
    //-------------------- Set Data --------------------------
      var data = new google.visualization.DataTable();
      data.addColumn('number', 'day');
      data.addColumn('number', '원');

      data.addRows([
        [0, 0],   [1, 10],  [2, 23],  [3, 17],  [4, 18],  [5, 9],
        [6, 11],  [7, 27],  [8, 33],  [9, 40],  [10, 32], [11, 35],
        [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48],
        [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57],
        [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53],
        [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65],
        [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65],
        [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70],
        [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70],
        [54, 71], [55, 72], [56, 73], [57, 75], [58, 70], [59, 68],
        [60, 64], [61, 60], [62, 65], [63, 67], [64, 68], [65, 69],
        [66, 70], [67, 72], [68, 75], [69, 80]
      ]);

      //-------------------- Set Option --------------------------
      var options = {
    	      legend: {
    	          position: 'none'
    	        }, //non-display column name
    	      series:{
    	      	0: {color: '#13bfd7'},
    	      } //graph color
      };

      //-------------------- Draw LineChart --------------------------
	  function resize() {
      	var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      	chart.draw(data, options);
	  }
	  
      //반응형
      window.onload = resize();
      window.onresize = resize;

}
</script>


</body>
</html>