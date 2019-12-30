// 메모 목록 로드
function load() {
	var trTemplate = document.querySelector(".tr-template-list");
    var content = document.querySelector(".content");
	var request = new XMLHttpRequest();
	
    request.open("GET", "captureMemo-json", true);
    request.onload = function() {
        var list = JSON.parse(request.responseText);

        content.innerHTML = "";

		for(var i = 0; i < list.length; i++) {
			var cloneTr = document.importNode(trTemplate.content, true);
			var tds = cloneTr.querySelectorAll("td");
			
			tds[0].innerText = list[i].codeNumName;
			tds[1].innerText = list[i].title;
			var temp = document.createTextNode(list[i].regdate);
			tds[2].firstElementChild.before(temp);

			content.append(cloneTr);
		}
    };
    request.onerror = function() {
        alert("로딩 실패");
    };
    request.send();
}

window.addEventListener("load", function(e) {
	load();

	var content = this.document.querySelector(".content");
	var trTemplate = document.querySelector(".tr-template");
	var prevMemo;
	// var tid;

    content.onclick = function(e) {
        var captTitle = e.target;

        // if(tid != null){
        // 	clearTimeout(tid);
        // 	tid = null;
        // }

        if (!(captTitle.nodeName == "TD")) return;

        if (captTitle.parentNode.nextElementSibling != null)
            if (captTitle.parentNode.nextElementSibling.className != "parent") {
                if (prevMemo != null) prevMemo.remove();
                return;
            }

        if (prevMemo != null) prevMemo.remove();

        var cloneTr = document.importNode(trTemplate.content, true);

        captTitle.parentElement.after(cloneTr);

        if (content.querySelector(".child") != null)
            prevMemo = content.querySelector(".child").parentElement;

        // console.log(prevMemo.style.height);
        // tid = setTimeout(function(){
        // 	prevMemo.className ="child-tr2";
        // 	console.log(prevMemo);
        // 	tid = null;
        // }, 500);
    };
});

window.addEventListener("message", function(e) {
    //json format
    //CaptureMemo PER/PBR/ROE/debtRatio/marketCap/codeNum/memberID
    var data = e.data.capture;

    if (data) {
        //		console.log(data);

        var request = new XMLHttpRequest();

        request.open("POST", "captureMemo-json", true);
        request.setRequestHeader(
            "Content-Type",
            "application/x-www-form-urlencoded"
        );
        request.onload = function() {
            console.log(request.responseText);
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
