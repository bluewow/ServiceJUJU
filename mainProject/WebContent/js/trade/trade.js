var codeNum = "005930"; //삼성전자

window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) { //codeNum
		codeNum = e.data;
		
		asyncTrade("pass", 0, e.data);		// 매수/매도 창 데이터 갱신
	}
});

window.addEventListener("load", function(){
	bb.defaults();
	chartUpdate();
	asyncTrade("pass", 0);		
	tradeFunc();		// 매수/매도 이벤트 처리


});

function chartUpdate() {
	chartSell();
	chartBuy();
}

bb.defaults({
	data: {
		type: "bar",
		labels:{
			colors : "black",
			centered : true,
		  	position:{ x:10 },
		},
	},
	axis: {
        rotated: true,
        y: {
            show: false,
            max: 6000,
        },
        x: { type: "category" }
    },
    legend: { show: false },
    tooltip: { show: false },
    bar: { padding: 5 },
    size: {
    	width: 200,
        height: 350
    },
//    onresize: function(ctx) {
//    	//TODO
//    }
	
});

function chartSell() {
	var chart = bb.generate({
		bindto : "#chartSell",
	    data: {
	    	x: "x",
	        columns: [	   
	        	["x",2200,2300,2400,2500,2550,2600,2650,2700,2750,2800],
	        	["data", 100,8000,700,16,51,21,45,66,237,1300],
	        ],
	        colors:{data:"#2D9AF277"}
	    },
	    title: {
	        text: "매도잔량"
	    }
	});
}

function chartBuy() {
	var chart = bb.generate({
		bindto : "#chartBuy",
	    data: {
	    	x: "x",
	        columns: [	            
	        	["x",1650,1700,1750,1800,1850,1900,1950,2000,2050,2100],
	        	["data", 1,0,2,4,5,6,7,8,9,2500],
	        ],	
	        colors:{data:"#F4000677"}
	    },
	    title: {
	        text: "매수잔량"
	    }
	});
}

function asyncGraph() { 
    var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?graph=" + codeNum);
    ajax.onload = function() {
    	google.charts.setOnLoadCallback(
    			drawBasic(JSON.parse(ajax.responseText)));
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
        data[0].innerHTML = obj.vMoney.toLocaleString() + "원";
        data[1].innerHTML = obj.quantity.toLocaleString() + "주";
//        data[2].innerHTML = obj.vMoney.toLocaleString() + "원";
//        data[3].value = "";

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
//	var button = document.querySelector("#page-bottom-box");
//	var event = button.querySelector("input.event");
//	var data = button.querySelectorAll(".data");
//	var qty = button.querySelector("#text");
//	var sellButton = button.querySelector("#sell");
//	
//	button.onclick = function(e) {
//		if(e.target.name != event.name)
//	      	return;
//	    
//		if(!data[3].value) {
//			alert("수량을 입력해 주세요");
//			return;
//		}
//		
//		if(data[3].value <= 0) {
//			alert("잘못된 수량입력 입니다");
//			data[3].value = "";
//			return;
//		}
//		
//		if(e.target.value == "매       수") {
//			var trade = "buy";
//			if(confirm(data[3].value + "주 매수를 진행하시겠습니까?") == false) {
//				data[3].value = "";
//				return;
//			}
//			
//		}
//		else if(e.target.value =="매       도") {
//			var trade = "sell";
//			if(confirm(data[3].value + "주 매도를 진행하시겠습니까?") == false) {
//				data[3].value = "";
//				return;
//			}
//		}
//			
//		asyncTrade(trade, qty.value);
//	}
}

//function incrementValue(e) {
//	e.preventDefault();
//	var fieldName = $(e.target).data('field');
//	var parent = $(e.target).closest('div');
//	var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);
//	
//	if (!isNaN(currentVal)) {
//	  parent.find('input[name=' + fieldName + ']').val(currentVal + 1);
//	} else {
//	  parent.find('input[name=' + fieldName + ']').val(0);
//	}
//}
//
//function decrementValue(e) {
//	e.preventDefault();
//	var fieldName = $(e.target).data('field');
//	var parent = $(e.target).closest('div');
//	var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);
//	
//	if (!isNaN(currentVal) && currentVal > 0) {
//	  parent.find('input[name=' + fieldName + ']').val(currentVal - 1);
//	} else {
//	  parent.find('input[name=' + fieldName + ']').val(0);
//	}
//}
//
//$('.input-group').on('click', '.button-plus', function(e) {
//	  incrementValue(e);
//	});
//
//	$('.input-group').on('click', '.button-minus', function(e) {
//	  decrementValue(e);
//	});

	
