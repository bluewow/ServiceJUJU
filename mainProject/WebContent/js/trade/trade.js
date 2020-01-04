var codeNum = "005930"; //삼성전자

window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) { //codeNum
		codeNum = e.data;
		updateEvent();
		
		asyncTrade("pass", 0, e.data);		// 매수/매도 창 데이터 갱신
	}
});

window.addEventListener("load", function(){
	updateEvent();
	
	asyncTrade("pass", 0);		
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
}

function updateEvent() {
	var stockNameDiv = document.querySelectorAll("#stockName div");
	var stockNameSpan = document.querySelectorAll("#stockName span");

	var ajax = new XMLHttpRequest();
	ajax.open("GET", "../../card/trade/trade?codeNum=" + codeNum);
	ajax.onload = function() {
		var obj = JSON.parse(ajax.responseText);
		stockNameDiv[0].innerHTML = obj.name;
		stockNameDiv[1].innerHTML = obj.price.toLocaleString();
		if(obj.status == "up") {
			stockNameSpan[0].classList.add("fa", "fa-caret-up");
			stockNameSpan[1].innerHTML = obj.unit.toLocaleString();
			stockNameSpan[2].innerHTML = "(+"+ obj.ratio + "%)";
			for(var i = 0; i < stockNameSpan.length; i++) {
				stockNameSpan[i].style.color = "red";
			}
		}
		if(obj.status == "down") {
			stockNameSpan[0].classList.add("fa", "fa-caret-down");
			stockNameSpan[1].innerHTML = obj.unit.toLocaleString();
			stockNameSpan[2].innerHTML = "(-"+ obj.ratio + "%)";
			for(var i = 0; i < stockNameSpan.length; i++) {
				stockNameSpan[i].style.color = "blue";
			}
		}
	}
	ajax.send();
}

function asyncTrade(p1, p2) { //p1=buy/sell p2=수량 p3=종목코드
	var button = document.querySelector("#page-bottom-box");
	var data = button.querySelectorAll(".data");
	var sellButton = button.querySelector("#sell");
	
	var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?replaceEvent=on&button="
    		+ p1 + "&Purse/Sold=" + p2 + "&codeNum" + codeNum );
    ajax.onload = function() {
    	var obj = JSON.parse(ajax.responseText);
        data[0].innerHTML = obj.avgPrice.toLocaleString() + "원";
        data[1].innerHTML = obj.quantity.toLocaleString() + "주";
        data[2].innerHTML = obj.vMoney.toLocaleString() + "원";
        data[3].value = "";

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

