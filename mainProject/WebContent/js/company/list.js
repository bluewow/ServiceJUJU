window.addEventListener("load", function(){
	//codeNum 전달이벤트
	sendEvent();
	
	function sendEvent() {
	    var companyName = document.querySelectorAll(".companyName");
	    if(companyName == null) 
	    	return;
	    
	    for(i = 0; i < companyName.length; i++) {
		    companyName[i].onclick = function(e){
		       var codenum = e.target.dataset.codenum;
		       var stockBoardWindow = parent.document.querySelector("#stock-board-window");
		       var analysisWindow = parent.document.querySelector("#analysis-window");
		       var tradeWindow = parent.document.querySelector("#trade-window");
		       
		       stockBoardWindow.contentWindow.postMessage(
		    		   codenum, "http://localhost:8080/card/board/stock_board.jsp");
		       analysisWindow.contentWindow.postMessage(
		    		   codenum, "http://localhost:8080/card/trade/analysis.jsp");
		       tradeWindow.contentWindow.postMessage(
		    		   codenum, "http://localhost:8080/card/trade/trading.jsp");
		    }
	    }
	}
	
	
	var UncertifiedLogin = document.querySelector("#UncertifiedLogin");
	var certifiedLoing = document.querySelector("#certifiedLoing");
	
	if (UncertifiedLogin != null) {
		UncertifiedLogin.onclick = function(){
			alert("로그인이 필요한 서비스입니다.");
		};
	}
	
	if (certifiedLoing != null) {
		certifiedLoing.onclick = function(e){
			alert("로그인 됨");
			
			var attention = document.querySelectorAll(".attention");
		    if(attention == null) 
		    	return;
			
		    for (var i = 0; i < attention.length; i++) {
		    	attention[i].onclick = function(e){
		    		 var attention = e.target.dataset.attention;
		    		 var interestlistWindow = parent.document.querySelector("#interestlist-window");
		    		 
		    		 console.log(attention);
		    		 
		    		 interestlistWindow.contentWindow.postMessage(
		    				 attention, "http://localhost:8080/card/board/stock_board.jsp");
		    	};
			};
			
		};
		
	};
	
 });



