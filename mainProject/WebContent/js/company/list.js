window.addEventListener("load", function(){
	//codeNum 전달이벤트
	sendEvent();
	
	function sendEvent() {
	    var companyName = document.querySelector("#companyName");
	    
	    if(companyName == null) 
	    	return;
	    
	    companyName.onclick = function(e){
	       var codenum = companyName.dataset.codenum;
	       
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
});