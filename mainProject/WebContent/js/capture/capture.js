class CaptureMemo {
	constructor(){
		this.prevMemo;
		this.content = $(".content");
		this.trTemplate = document.querySelector(".tr-template");
	}

	setPrevMemo(prevMemo) {
		this.prevMemo = prevMemo;
	}

	loadList() {
		$.getJSON("captureMemo-json")
		.done(function(list) {
			let trTemplate = document.querySelector(".tr-template-list");
			let content = $(".content");
			
			content.html("");
		
			for(let i = 0; i < list.length; i++) {
				let cloneTr = document.importNode(trTemplate.content, true);
				let tds = $(cloneTr).children();
				
				tds.children().eq(0).text(list[i].codeNumName);
				tds.children().eq(1).text(list[i].title);
				tds.children().eq(2).children().first().before(list[i].regdate);
				tds.attr("dataset.id", list[i].id);
				content.append(cloneTr);
			}
		})
		.fail(function() {
			alert("로딩 실패");
		});
	}

	createDetail(result, target){
		if (target.parent().next().attr("class") == "child-tr") {
			if (this.prevMemo.length != 0) {
				this.prevMemo.remove();
				this.prevMemo = undefined;
			}
			return;
		}
		let clone = document.importNode(this.trTemplate.content, true);
		
		let title = $(clone).find(".memo > div:first-child input");
		let memoContent = $(clone).find(".memo > hr + div textarea");
		title.val(result.title);
		memoContent.val(result.content);
		
		target.parent().get(0).after(clone);
						
		if (this.content.find(".child").first().length != 0)
			this.prevMemo = this.content.find(".child").first().parent();
	}

	getDetail(target){
		return new Promise(function(resovle, reject){
			let memoId = target.parent().attr("dataset.id");
			
			$.getJSON("captureMemo-detail-json?memoId=" + memoId)
			.done(function(result) {
				resovle(result);
			})
			.fail(function() {
				alert("로딩 실패");
			});
		}.bind(this));
	}

	updateDetail(target){
		var data = {};
		data.id = target.parent().attr("dataset.id");
		data.title = $(".memo > div").eq(0).children().first().val();
		data.content = $(".memo > div").eq(1).children().first().val();
		console.log(data);
		
		$.post("captureMemo-json-update", JSON.stringify(data))
		.done(function(result) {
			if(result == 1) {
				target.parent().find("td").eq(1).text($(".memo > div").eq(0).children().first().val());
			}
		})
		.fail(function() {
			alert("수정 실패");
		})
	}

	deleteDetail(){
		$.post("captureMemo-delete-json", "memoId=" + target.attr("dataset.id"))
		.done(function() {
			captureMemo.loadList();
		})
		.fail(function() {
			alert("삭제 실패");
		});
	}
}

$(function() {
	let captureMemo = new CaptureMemo();
	captureMemo.loadList();

    captureMemo.content.click(function(e) {
        let target = $(e.target);

		switch(target.prop("nodeName")) {
			case "TD":	
				// detail 펼치기
				captureMemo.getDetail(target)
				.then(function(result){
					captureMemo.createDetail(result, target);
				})
				.then(function(){
					// 메모 수정
					$(".button").click(function() {
						captureMemo.updateDetail(target);
					});
				});
				break;
			case "SPAN":	// 메모 삭제
				captureMemo.deleteDetail();
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
