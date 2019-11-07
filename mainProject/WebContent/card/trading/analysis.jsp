<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

<!-- ref analysis.css --> 
<link rel="stylesheet" href="../../css/trading/analysis.css">

<!-- Google Chart -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type-"text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
	         ['Element', 'Density', { role: 'style' }],
	         ['Copper', 8.94, '#b87333'],            // RGB value
	         ['Silver', 10.49, 'silver'],            // English color name
	         ['Gold', 19.30, 'gold'],
	      ]);
		var options = {
				title : "test",
				vAxis: {title: 'cups'},
				hAxis: {title: 'Month'},
				seriesType: 'bars',
				series: {4: {type: 'line'}}
		}
		
		var chart = new google.visualization.ComboChart(document.getElementById('stockPredictBar'));
		chart.draw(data, options);
	}
</script>
</head>
<body>
	<div class="a1">
		<header class="stockName">
			<div>기현 알고리즘</div>
			<div>204,000</div>
		</header>
	</div>


	<div class="a2 stockName">차트1
	</div>
	<div class="a3">차트2
	</div>


	<div class="a4">차트3
	</div>
</body>
</html>