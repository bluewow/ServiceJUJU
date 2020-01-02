window.addEventListener("load", function() {
	var chartA = bb.generate({
		bindto : "#pieChartA",
		size : {
			height : 150,
			width : 120
		},
		data : {
			columns : [ [ "data1", 30 ], [ "data2", 120 ] ],
			type : "donut",
		},
		donut : {
			title : "종목 동향",
			label : {
				show : false,
			},
		},
		legend : {
			show : false
		}
	});

	var chartB = bb.generate({
		bindto : "#pieChartB",
		size : {
			height : 150,
			width : 120
		},
		data : {
			columns : [ [ "data1", 30 ], [ "data2", 120 ] ],
			type : "donut",
		},
		donut : {
			title : "수급",
			label : {
				show : false,
			},
		},
		legend : {
			show : false
		}
	});

	var chartC = bb.generate({
		bindto : "#pieChartC",
		size : {
			height : 150,
			width : 120
		},
		data : {
			columns : [ [ "data1", 30 ], [ "data2", 120 ] ],
			type : "donut",
		},
		donut : {
			title : "컨텐츠",
			label : {
				show : false,
			},
		},
		legend : {
			show : false
		}
	});

	var timeChart = bb.generate({
		bindto : "#timeChart",// +거래량 콤보
		data : {
			x : "x",
			columns : [
					[ "x", "2013-01-01", "2013-01-02", "2013-01-03",
							"2013-01-04", "2013-01-05", "2013-01-06" ],
					[ "data1", 30, 200, 100, 400, 150, 250 ],
					[ "data2", 130, 340, 200, 500, 250, 350 ] ]
		},
		axis : {
			x : {
				type : "timeseries",
				tick : {
					format : "%Y-%m-%d"
				}
			}
		},
		legend : {
			position : "right"
		},
		size : {
			height : 210,
			width : 500
		},
		resize : {
			auto : true
		},
		padding : {
			top : 10,
			bottom : 10
		}
	});

	setTimeout(function() {
		chart.load({
			columns : [ [ "data3", 400, 500, 450, 700, 600, 500 ] ]
		});
	}, 1000);

	var chart = bb.generate({
		bindto : "#side",
		data : {
			columns : [ [ "data", 91.4 ] ],
			type : "gauge",
		},
		gauge : {},
		color : {
			pattern : [ "#FF0000", "#F97600", "#F6C600", "#60B044" ],
			threshold : {
				values : [ 30, 60, 90, 100 ]
			}
		},
		size : {
			height : 150,
		// width : 150
		},
	});
});