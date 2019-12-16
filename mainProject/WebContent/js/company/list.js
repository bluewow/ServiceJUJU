window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName");
    var CompanyNameClickedByUser = document.querySelector("#companyName");
    
    /*CompanyNameClickedByUser.onclick = function(e){
       alert("클릭 확인")
       var ajax = new XMLHttpRequest();
       ajax.open("GET", "../../card/trade/list?CompanyNameClickedByUser=on");
       ajax.onload = function() {
			//data send to capture Card
			var frame = parent.document.querySelector("#companyListWindow");
			frame.contentWindow.postMessage(
					codeNum = ajax.responseText  , 
					"http://localhost:8080/card/trade/analysis.jsp");
		}
       ajax.send();
    };*/
});