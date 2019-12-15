window.addEventListener("load", function(){ 
	//캡쳐하기
	captureButton();
	
	function captureButton() {
		var button = document.querySelector("#capture");
		
		button.onclick = function(e) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", "../../card/trade/analysis?capture=on");
			ajax.onload = function() {
				//data send to capture Card
				var frame = parent.document.querySelector("#captureWindow");
				frame.contentWindow.captureEvent(ajax.responseText);
			}
			ajax.send();
		}
	}
	
});
