window.addEventListener("load", function(){ 
	//캡쳐하기
	captureButton();
	
	function sendData(object) {
		var ajax = new XMLHttpRequest();
		ajax.open("POST", "/card/capturememo/captureMemo");
        ajax.setRequestHeader('Content-Type', 'application/json');
		ajax.onload = function() {
			console.log(object);
		}
		ajax.send(object);
	}
	
	function captureButton() {
		var button = document.querySelector("#capture");
		
		button.onclick = function(e) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", "../../card/trade/analysis?capture=on");
			ajax.onload = function() {
				sendData(ajax.responseText);
				
//				var newsFrame = parent.document.querySelector("#news");
//				var newsWindow = newsFrame.contentWindow || newsFrame;
//				newsWindow.test("1");
			}
			ajax.send();
		}
	}
	
});
