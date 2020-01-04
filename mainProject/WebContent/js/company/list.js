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
	
	
	var UncertifiedLogin = document.querySelectorAll("#UncertifiedLogin");
	var certifiedLoing = document.querySelector("#certifiedLoing");
	// 로그인이 안됐을 경우
	if (UncertifiedLogin != null) {
		for (var i = 0; i < UncertifiedLogin.length; i++){
			UncertifiedLogin[i].onclick = function(){
				alert("로그인이 필요한 서비스입니다.");
			};
		}
	};
	// 로그인이 됐을 경우
	if (certifiedLoing != null) {
		certifiedLoing.onclick = function(e){
			
			var certifiedLoing = document.querySelectorAll("#certifiedLoing");
		    if(certifiedLoing == null) 
		    	return;
			// 관심을 querySelectorAll로 전체 선택한 다음에 사용자가 클릭 했을 경우 클릭한 회사 종목 코드를 관심목록 카드로 넘기는 코드
		    for (var i = 0; i < certifiedLoing.length; i++) {
		    	certifiedLoing[i].onclick = function(e){
		    		 var checking = true;
		    		 var attention = e.target.dataset.attention;
		    		 var interestlistWindow = parent.document.querySelector("#interestlist-window");
		    		 
		    		 console.log(attention);
		    		 
		    		 interestlistWindow.contentWindow.postMessage(
		    				 attention, "http://localhost:8080/card/board/stock_board.jsp");
		    		 
		    		 // ajax로 종목 코드를 컨트롤러에 전달한 뒤에 데이터 베이스에 저장하는 코드
		    		 var request = new XMLHttpRequest();
		    		 request.open("GET", "../../card/company/list-json?attention="+attention);
		    		 console.log(request.readyState);
		    		 request.onload = function(){
		    			 if (checking == true) {
							alert("즐겨찾기에 추가되었습니다");
							checking = false;
						} else if (checking == false){
							alert("즐겨찾기에 삭제되었습니다.")
						}
		    			 
		    		 }
		    		 request.send();
		    	};
			};
			
			
			
			
		};
		
	};
	
 });



