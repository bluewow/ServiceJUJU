var codeNum = "005930"; //삼성전자

window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) { //codeNum
		codeNum = e.data;
		
		updateStatus();		// 매수/매도 창 데이터 갱신
	}
});

window.addEventListener("load", function(){
	bb.defaults();
	chartSell = bb.generate({
		bindto : "#chartSell",
	    data: {
	    	x: "x",
	        columns: [	   
	        	["x"],
	        	["data"],
	        ],
	        colors:{data:"#2D9AF277"}
	    },
	});

	chartBuy = bb.generate({
		bindto : "#chartBuy",
	    data: {
	    	x: "x",
	        columns: [	            
	        	["x"],
	        	["data"],
	        ],	
	        colors:{data:"#F4000677"}
	    },
	});
	
	updateStatus();
	buttonEvent();
//	tradeFunc();		// 매수/매도 이벤트 처리

});

function buttonEvent() {
	var arrow = document.querySelectorAll("i");
	
	arrow[0].onclick = function(e) {
//	   console.log(e);

	};
	arrow[1].onclick = function(e) {
//		console.log(e);
	}
	arrow[2].onclick = function(e) {
//		console.log(e);
	}
	arrow[3].onclick = function(e) {
//		console.log(e);
	}
}

function updateStatus() {
	var button = document.querySelector("#page-bottom-box");
	var data = button.querySelectorAll(".data");
	var sellButton = button.querySelector("#sell");
	
	var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?&codeNum=" + codeNum );
    ajax.onload = function() {
    	var obj = JSON.parse(ajax.responseText);
    	var sellPrice = new Array("x");
    	var sellQty = new Array("data");
    	var buyPrice = new Array("x");
    	var buyQty = new Array("data");
    	
    	data[0].innerHTML = obj.vMoney.toLocaleString() + "원";
        data[1].innerHTML = obj.qty.toLocaleString() + "주";
        
        if(obj.sellPrice) {
	    	for(var i=0; i < obj.sellPrice.length; i++) {
	    		sellPrice.push(obj.sellPrice[i]);
	    		sellQty.push(obj.sellQty[i]);
	    	}
        } 
        if(obj.buyPrice) {
	    	for(var i=0; i < obj.buyPrice.length; i++) {
	    		buyPrice.push(obj.buyPrice[i]);
	    		buyQty.push(obj.buyQty[i]);
	    	}
        }
        
        bb.instance[0].load({
    		columns: [sellPrice, sellQty],
    	});
        
    	bb.instance[1].load({
    		columns: [buyPrice, buyQty]
    	});
    	
    	/*var obj = JSON.parse(ajax.responseText);
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
        }*/
    }    
    ajax.send();
}

bb.defaults({
	data: {
		type: "bar",
		labels:{
			colors : "black",
			centered : true,
		  	position:{ x:15 },
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
    render: {
    	   observe: false
    	},
    onrendered: function() {
    	this.main.selectAll(".bb-texts text").each(function(d) {
    		const x = +this.getAttribute("x");

    		this.setAttribute("x", (
    			d.value >= 6000 ? 0 : 15
    		));
    	});
    },
    legend: { show: false },
    tooltip: { show: false },
    bar: { padding: 5 },
    size: {
    	width: 200,
        height: 350
    },
});


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

	
