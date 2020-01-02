var codeNum = "005930"; //삼성전자

window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) { //codeNum
		codeNum = e.data;
//		asyncGraph(e.data);					// 종목에 맞는 그래프로 변경
		asyncTrade("pass", 0, e.data);		// 매수/매도 창 데이터 갱신
	}
});

window.addEventListener("load", function(){
	//load google chart
	google.charts.load('current', {packages: ['corechart', 'line']});
//	asyncGraph(codeNum);
	asyncTrade("pass", 0);		
	dailyGraphFunc();	// 그래프 변경
	tradeFunc();		// 매수/매도 이벤트 처리


});

function asyncGraph() { 
    var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?graph=" + codeNum);
    ajax.onload = function() {
    	google.charts.setOnLoadCallback(
    			drawBasic(JSON.parse(ajax.responseText)));
    }
    ajax.send();
    
	//그림그리기 위한 data
	function drawBasic(list) {

	//-------------------- Set Data --------------------------
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'day');
	  data.addColumn('number', '원');

	  data.addRows(list);
	  
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
}

function asyncTrade(p1, p2) { //p1=buy/sell p2=수량 p3=종목코드
	var button = document.querySelector("#page-bottom-box");
	var name = document.querySelectorAll("#stockName div"); 
	var data = button.querySelectorAll(".data");
	var sellButton = button.querySelector("#sell");
	
	var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?replaceEvent=on&button="
    		+ p1 + "&Purse/Sold=" + p2 + "&codeNum=" + codeNum);
    ajax.onload = function() {
    	var obj = JSON.parse(ajax.responseText);
        data[0].innerHTML = obj.avgPrice.toLocaleString() + "원";
        data[1].innerHTML = obj.quantity.toLocaleString() + "주";
        data[2].innerHTML = obj.vMoney.toLocaleString() + "원";
        data[3].value = "";
        name[0].innerHTML = obj.name;
        name[1].innerHTML = "";
        
		if(data[1].innerHTML == "0주") {
			sellButton.className = "event button button-button shadow"
			sellButton.disabled = true;
		} else {
			sellButton.className = "event button button-button animation"
			sellButton.disabled = false;
		}
        
		//result - 0:ok, 1:vmoney부족, 2: 거래정지목록, 3:장내시간이 아님, 
        //		   4:수량이 0이하인 경우 거래x, 5:수량이 0이 되는 경우  6:보유종목이 아닌경우 거래x
        switch(obj.result) {
        case 0:
        case 5:
        	alert("체결되었습니다");
        	var frame = parent.document.querySelector("#holding-window");
			frame.contentWindow.postMessage(
					obj.codeNum , 
					"http://localhost:8080/card/managestocks/holdinglist.jsp");
        	break;
        case 1:
        	alert("가상머니가 부족합니다");
        	break;
        case 4:
        	alert("보유수량을 초과하였습니다");
        	break;
        case 6:
        	alert("현재 보유종목이 아닙니다.");
        	break;
        case 99:	//매수매도창 refresh
        	break;
    	default:
    		break;
        }
    }    
    ajax.send();
}

//그래프 변경
function dailyGraphFunc() {
    var dateButton = document.querySelector(".page-mid");

    dateButton.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
        	return;
        
        e.preventDefault();
        asyncGraph(e.target.value);
    }
}

//  매수/매도 이벤트 처리
function tradeFunc() {
	var button = document.querySelector("#page-bottom-box");
	var event = button.querySelector("input.event");
	var data = button.querySelectorAll(".data");
	var qty = button.querySelector("#text");
	var sellButton = button.querySelector("#sell");
	
	button.onclick = function(e) {
		if(e.target.name != event.name)
	      	return;
	    
		if(!data[3].value) {
			alert("수량을 입력해 주세요");
			return;
		}
		
		if(data[3].value <= 0) {
			alert("잘못된 수량입력 입니다");
			data[3].value = "";
			return;
		}
		
		if(e.target.value == "매       수") {
			var trade = "buy";
			if(confirm(data[3].value + "주 매수를 진행하시겠습니까?") == false) {
				data[3].value = "";
				return;
			}
			
		}
		else if(e.target.value =="매       도") {
			var trade = "sell";
			if(confirm(data[3].value + "주 매도를 진행하시겠습니까?") == false) {
				data[3].value = "";
				return;
			}
		}
			
		asyncTrade(trade, qty.value);
	}
}

