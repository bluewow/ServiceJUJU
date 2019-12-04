/*	
	drawBasic(); - 그림그리기 위한 data 함수
	graphFunc(); - 그리기 함수
	ajaxFunc(); - 일봉,주봉,월봉 클릭 및 그래프 변경(ajax)
*/

window.addEventListener("load", function(){
	//load google chart
	google.charts.load('current', {packages: ['corechart', 'line']});
	
	// 일봉,주봉,월봉 클릭 및 그래프 변경(ajax)
	ajaxFunc();
	
	// 매수/매도 이벤트 처리
	tradeFunc();
	
	 //그림그리기 위한 data
    function drawBasic(list) {
	
    //-------------------- Set Data --------------------------
      var data = new google.visualization.DataTable();
      data.addColumn('number', 'day');
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
	
	//일봉,주봉,월봉 클릭 및 그래프 변경(ajax)
	function ajaxFunc() {
	    var dateButton = document.querySelector(".page-mid");

	    dateButton.onclick = function(e) {
	        if(e.target.nodeName != "INPUT")
	        	return;
	        
	        e.preventDefault();
	        
	        if(e.target.value == "일봉") 
	        	selectedButton(0, "day_");
	        else if(e.target.value == "주봉")  
	        	selectedButton(1, "week_");
	        else if(e.target.value == "월봉")  
	        	selectedButton(2, "month_");
	        
	        var list =[];
	        var ajax = new XMLHttpRequest();
	        ajax.open("GET", "../../card/trade/trade?date=" + e.target.value);
	        ajax.onload = function() {
	        	console.log(JSON.parse(ajax.responseText));
	        	google.charts.setOnLoadCallback(drawBasic(JSON.parse(ajax.responseText)));
	        }
	        ajax.send();
	    	
	    }
	    
	    //버튼을 클릭시 class name 명을 변경하여 css 활성화한다.
		function selectedButton(index, string) {
			for(var i=0; i<3; i++) {
				if(index == i)
					dateButton.children[0][index].className = "button button-chart " + string ;
				else 
					dateButton.children[0][i].className = "button button-chart";
			}
        }
        
	}//finish ajax func
	
	//  매수/매도 이벤트 처리
	//	tradeFunc 
	function tradeFunc() {
		var button = document.querySelector("#page-bottom-box");
		var event = button.querySelector("input.event");
		var data = button.querySelectorAll(".data");
		
		button.onclick = function(e) {
			if(e.target.className != event.className)
		      	return;
		    
			var money = 100000;
			//입력 type="text" 는 제외하기 위하여 length - 1 을 추가
			for(var i = 0; i < data.length - 1; i++) { 
//				console.log(data[i].innerHTML);
//				data[i].innerHTML = money.toLocaleString();
			}
			
			var ajax = new XMLHttpRequest();
	        ajax.open("GET", "../../card/trade/trade?replaceEvent=" + "on");
	        ajax.onload = function() {
	        	console.log(ajax.responseText);
	        }
	        ajax.send();
		
		}
	}
});
