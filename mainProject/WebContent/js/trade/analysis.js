var codeNum = "005930"; // 삼성전자

window.addEventListener("message", function(e) {
	if (e.data && (e.data.length == 6)) { // codeNum
		codeNum = e.data;
		updateEvent();
		chartUpdate();
		
		var x = document.querySelectorAll("text");
	}
});

window.addEventListener("load", function() {
	bb.defaults();
	updateEvent();
	chartUpdate();
});

function updateEvent() {
	var name = document.querySelectorAll("#stockName div");

	var ajax = new XMLHttpRequest();
	ajax.open("GET", "../../card/trade/analysis?codeNum=" + codeNum);
	ajax.onload = function() {
		var obj = ajax.responseText;
		name[0].innerHTML = obj;
	}
	ajax.send();
}

bb.defaults({
	data : {
		columns : [ [ "", 0 ] ],
		type : "gauge",
	},
	gauge : {
		fullCircle : true,
		startingAngle : 0,
		expand : false, 
		label : {
			extents : function(value, isMax) {
				return null;
			}
		}
	},
	transition : { duration : 1500 },
	legend : { show : false },
	clipPath: false,
	tooltip: { show : false },
});

function chartUpdate() {
	var chartA = bb.generate({
		bindto : "#chartA",
		color : { pattern : [ "#FF7F0E"] },
		size : { height : 150, width : 150 },
		title: { text : "종목 동향"}
	});

	var chartB = bb.generate({
		bindto : "#chartB",
		color : { pattern : [ "#1F77B4"] },
		size : { height : 150, width : 150 },
		title: { text : "수급"}
	});
	

	var chartC = bb.generate({
		bindto : "#chartC",
		color : { pattern : [ "#2CA02C"] },
		size : { height : 150, width : 150 },
		title: { text : "컨텐츠"}
	});

	var chartD = bb.generate({
		bindto : "#chartD",
		color : { pattern : [ "#FF4040"] },
		size : { height : 150, width : 150 },
		title: { text : "강도"}
	});
	
	var chartE = bb.generate({
		bindto : "#chartE",
		title: { text : "분석 결과"},
		tooltip: { show : true },
		gauge : {
			fullCircle : false,
			startingAngle : -90,
			expand : false, 
			label: {
	            show: false
	        },
		},
		 color: {
			    pattern: [
			      "#2CA02C",
			      "#FF7F0E",
			      "#FF4040",
			    ],
			    threshold: {
			      values: [
			        33, 66, 100,
			      ] }
		 },
	});
	
	setTimeout(function() {
		chartA.load({
			columns : [ [ "", 30 ] ]
		});
		chartB.load({
			columns : [ [ "", 70 ] ]
		});
		chartC.load({
			columns : [ [ "", 20 ] ]
		});
		chartD.load({
			columns : [ [ "", 20 ] ]
		});
		chartE.load({
			columns : [ [ "투자경고", 30 ] ]
		});
	}, 0);
}