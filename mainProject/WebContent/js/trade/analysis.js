window.addEventListener("load", function(){ 
	//캡쳐하기
	captureButton();
	
	function captureButton() {
		var button = document.querySelector("#capture");
		
		button.onclick = function(e) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", "../../card/trade/analysis?caputre=on");
			ajax.onload = function() {
				console.log(request.responseText);
			}
			ajax.send();
		}
	}
	
});