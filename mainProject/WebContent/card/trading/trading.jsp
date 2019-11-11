<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trading/trading.css">


<!--  팝업창 띄우기
 구글 Line + Control Chart -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  
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
		<form action="trade" method="get">
			<input class="button button-chart" type="submit" name="date" value="일봉">
			<input class="button button-chart" type="submit" name="date" value="주봉">
			<input class="button button-chart" type="submit" name="date" value="월봉">
		</form>
		<div id="chart_div"></div>
	</section>
	
	<!-- --------------- page-bottom -------------- -->
		<section class="page-bottom">
		<div id="page-bottom-box">
			<div class="show-button-align">
				<input class="button button-status" type="button" value="자산 상황">
				<div>176,000</div>
				<input class="button button-status" type="button" value="구매 수량">
				<div>
					<input id="text" autocomplete="off">
				</div>
				<form action="trade" method="get">
					<input class="button button-button" type="submit" name="buy" value="매       수">
				</form>
			</div>
			<div class="show-button-align">
				<input class="button button-status" type="button" value="보유 상황">
				<div>0</div>	
				<input class="button button-status" type="button" value="매도 수량">
				<div>
					<input id="text" autocomplete="off">
				</div>
				<form action="trade" method="get">
					<input class="button button-button" type="submit" name="sell" value="매       도">
				</form>
			</div>
		</div>
	</section>
</div>	
</body>

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

</html>