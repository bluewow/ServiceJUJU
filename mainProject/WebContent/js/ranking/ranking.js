window.addEventListener("message", function (e) {
	if (e.data[0] == "selectPhoto"){
	console.log("selectPhoto : " + e.data[1]);
	}

});
