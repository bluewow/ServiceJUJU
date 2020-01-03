// 메모 목록 로드
function load() {
	$.getJSON("captureMemo-json")
	.done(function (list) {
		var trTemplate = document.querySelector(".tr-template-list");
		var content = $(".content");
		
		content.html("");
	
		for(var i = 0; i < list.length; i++) {
			var cloneTr = document.importNode(trTemplate.content, true);
			var tds = $(cloneTr).children();
			
			tds.children().eq(0).text(list[i].codeNumName);
			tds.children().eq(1).text(list[i].title);
			tds.children().eq(2).children().first().before(list[i].regdate);
			tds.children().eq(2).children().first().attr("dataset.id", list[i].id);
	
			content.append(cloneTr);
		}
	})
	.fail(function () {
		alert("로딩 실패");
	});
}

$(function(e) {
	load();

	var content = $(".content");
	var trTemplate = document.querySelector(".tr-template");
	var prevMemo;
	// var tid;

    content.click(function(e) {
        var target = $(e.target);

        // if(tid != null){
        // 	clearTimeout(tid);
        // 	tid = null;
		// }
		switch(target.prop("nodeName")) {
			case "TD":	// detail 펼치기
				if (target.parent().next().length != 0) {
					if (target.parent().next().attr("class") != "parent") {
						if (prevMemo.length != 0) prevMemo.remove();
						return;
					}					
				}
				
				if (prevMemo != null) prevMemo.remove();
				
				var clone = document.importNode(trTemplate.content, true);
				target.parent().get(0).after(clone);
				
				if (content.find(".child").first().length != 0)
					prevMemo = content.find(".child").first().parent();
				
				// console.log(prevMemo.style.height);
				// tid = setTimeout(function(){
					// 	prevMemo.className ="child-tr2";
					// 	console.log(prevMemo);
					// 	tid = null;
					// }, 500);

				// 메모 수정
				$(".button").click(function () {
					var data = {};
					data.id = target.parent().find("td").last().children().attr("dataset.id");
					data.title = $(".memo > div").eq(0).children().first().val();
					data.content = $(".memo > div").eq(1).children().first().val();
					
					$.post("captureMemo-json-update", JSON.stringify(data))
					.done(function (result) {
						if(result == 1) {
							target.parent().find("td").eq(1).text($(".memo > div").eq(0).children().first().val());
						}
					})
					.fail(function () {
						alert("수정 실패");
					})
				});

				break;
			case "SPAN":	// 메모 삭제
				$.post("captureMemo-json-del", "memoId=" + target.attr("dataset.id"))
				.done(function() {
					load();
				})
				.fail(function() {
					alert("삭제 실패");
				});
				break;
		}
	});
});

window.addEventListener("message", function(e) {
    //json format
    //CaptureMemo PER/PBR/ROE/debtRatio/marketCap/codeNum/memberID
    var data = e.data.capture;

    if (data) {
        var request = new XMLHttpRequest();

        request.open("POST", "captureMemo-json", true);
        request.setRequestHeader(
            "Content-Type",
            "application/x-www-form-urlencoded"
        );
        request.onload = function() {
            if (request.responseText == 1) load();
            else alert("캡쳐하기 실패");
        };

        request.send(data);
    }
});

// google.charts.load('current', {'packages':['corechart']});
//       google.charts.setOnLoadCallback(drawChart);

//       function drawChart() {
//         var data = google.visualization.arrayToDataTable([
//           ['Year', 'Sales', 'Expenses'],
//           ['2013',  1000,      400],
//           ['2014',  1170,      460],
//           ['2015',  660,       1120],
//           ['2016',  1030,      540]
//         ]);

//         var options = {
// 		  title: 'Company Performance',
// 		  backgroundColor: {fill: "#efefef"},
//           hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
//           vAxis: {minValue: 0}
//         };

//         var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
//         chart.draw(data, options);
//       }
