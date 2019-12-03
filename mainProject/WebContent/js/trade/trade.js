/*	
	drawBasic(); - 그림그리기 위한 data 함수
	graphFunc(); - 그리기 함수
	ajaxFunc(); - 일봉,주봉,월봉 클릭 및 그래프 변경(ajax)
*/

window.addEventListener("load", function(){
	//load google chart
	google.charts.load('current', {packages: ['corechart', 'line']});
	
	//일봉,주봉,월봉 클릭 및 그래프 변경(ajax)
	ajaxFunc();
	
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
});
