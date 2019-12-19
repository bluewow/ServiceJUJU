window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName");
    var CompanyNameClickedByUser = document.querySelector("#companyName");
    
    CompanyNameClickedByUser.onclick = function(e){
       alert("클릭 확인")
       var ajax = new XMLHttpRequest();
       ajax.open("POST", "../../card/company/list");
       ajax.onload = function() {
    	   var data ='';
		   var frame = parent.document.querySelector("#companyListWindow");
			frame.contentWindow.postMessage(
					{capture: ajax.responseText }, 
					"http://localhost:8080/card/capturememo/captureMemo.jsp");
		}
       
       ajax.send();
    };
});