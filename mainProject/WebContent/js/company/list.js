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
	var certifiedLoing = document.querySelectorAll("#certifiedLoing");
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
		    		 
		    		 
		    		// ========= 즐겨찾기 추가 ==================
	    			if (e.target.classList.contains("interest_no")) {
	    				var data = [
	    					 ["attention" ,attention],
	    					 ["status", "insert"]
	    				 ]
	    				 
	    				 var sendData = [];
	    				 
	    				 for (var i = 0; i < data.length; i++) 
	    						sendData[i] = data[i].join("=");
	    					
	    				  sendData = sendData.join("&");
	    				  
	    				  var request = new XMLHttpRequest();
	    				  request.open("POST", "../../card/company/list-json");
	    				  request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    				  request.send(sendData);
	    				  
	    				  request.onload = function () {
	    					alert("추가되었습니다.");
	    					e.target.classList.remove("interest_no");
	    					e.target.classList.add("interest_yes");
	    				  }
	    				
	    				
					} else if(e.target.classList.contains("interest_yes")){
						
						var data = [
							["attention", attention],
							["status", "delete"]
						]
						var sendData = [];

						for (var i = 0; i < data.length; i++) {
							sendData[i] = data[i].join("=");
						}
						sendData = sendData.join("&");

						var request = new XMLHttpRequest();
						request.open("POST", "../../card/company/list-json", true);
						request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
						request.send(sendData);

						// 3. 요청이 완료되었는지 결과를 확인한다.
						request.onload = function () {
							alert("삭제되었습니다.");
							e.target.classList.remove("interest_yes");
							e.target.classList.add("interest_no");
						};
			
			
					}
			
		    	};
	};	
	};
	// 즐겨찾기 추가
	 var interestNoClickHandler = function (e) {
		 
		 var attention = e.target.dataset.attention;
		 var data = [
			 ["attention" ,attention ]
			 ["status", "insert"]
		 ]
		 
		 var sendData = [];
		 
		 for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
			}
		  sendData = sendData.join("&");
		  
		  var request = new XMLHttpRequest();
		  request.open("POST", "../../card/company/list-json");
		  request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		  request.send(sendData);
		  
		  request.onload = function () {
			alert("추가되었습니다.");
			e.target.classList.remove("interest_no");
			e.target.classList.add("interest_yes");
		  }
	 };
	 
	// ========= 즐겨찾기 삭제 ==================
		var interestYesClickHandler = function (e) {
			var attention = e.target.dataset.attention;
			
			var data = [
				["attention", attention],
				["status", "delete"]
			]
			var sendData = [];

			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
			}
			sendData = sendData.join("&");

			var request = new XMLHttpRequest();
			request.open("POST", "../../card/company/list-json", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);

			// 3. 요청이 완료되었는지 결과를 확인한다.
			request.onload = function () {
				alert("삭제되었습니다.");
				e.target.classList.remove("interest_yes");
				e.target.classList.add("interest_no");
			}
		};
	
 });



