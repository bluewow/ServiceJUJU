window.addEventListener("message", function(e) {
	console.log(e.data);
	var ajax = new XMLHttpRequest();
	ajax.open("GET","../../card/managestocks/interestlist?codeNum=" + e.data , true);
	
	ajax.send();
	

});