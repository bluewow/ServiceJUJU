window.addEventListener("message", function(e) {
	if(e.data.capture)
		console.log(e.data.capture); //json format
		
});
