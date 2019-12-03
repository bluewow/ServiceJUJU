window.addEventListener("load", function(){ 
	//캡쳐하기
	captureButton();
	
	function captureButton() {
		var button = document.querySelector("#capture");
		
		button.onclick = function(e) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", "../../card/trade/analysis?capture=on");
			ajax.onload = function() {
				console.log(ajax.responseText);

//				var newsFrame = parent.document.querySelector("#news");
//				var newsWindow = newsFrame.contentWindow || newsFrame;
//				newsWindow.test("1");
			}
			ajax.send();
		}
	}
	
});