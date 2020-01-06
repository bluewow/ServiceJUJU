window.addEventListener("message", function(e) {
	console.log(e.data);
	var ajax = new XMLHttpRequest();
	ajax.open("GET", "../../card/managestocks/interestlist-json?codeNum="+ e.data, true);

	ajax.send();

});

window.addEventListener("load", function() {
	var section = this.document.querySelector(".interestList");

	var tbody = section.querySelector("table tbody");

	function interestLoad() {

		var request = new XMLHttpRequest();
		request.open("GET", "../../card/managestocks/interestlist-json", true);

		// 서블릿의 실행이 완료되었을때 실행
		request.onload = function() {
			// var cardFooter = section.querySelector(".card-footer");

			var list = JSON.parse(request.responseText);
			console.log("크롤링 데이터:"+list[i].price+list[i].percent);
			tbody.innerHTML = "";

			for (var i = 0; i < list.length; i++) {

				var template = section.querySelector(".template");
				var cloneTr = document.importNode(template.content, true);
				var tds = cloneTr.querySelectorAll("td");
				var formData = section.querySelector("#deleteInput");

				tds[0].firstElementChild.innerText = list[i].stockName;

				if (list[i].gain == "상승") {
					tds[1].firstElementChild.innerText = list[i].price;
					tds[1].lastElementChild.innerText = list[i].percent;
				} else if (list[i].gain == "하강") {
					tds[2].firstElementChild.innerText = list[i].price;
					tds[2].lastElementChild.innerText = list[i].percent;
				} else {
					tds[3].firstElementChild.innerText = list[i].price;
					tds[3].lastElementChild.innerText = list[i].percent;
				}
				
				for (var j = 1; j <= 3; j++) {
					if (tds[j].firstElementChild.innerText == "") {
						tds[j].style.display = "none";
					}
				}
				
				formData.value = list[i].stockName	
				
			}
		};
		request.send();
	};
	
	setInterval(function() {
		interestLoad();
	}, 5000);
	
	interestLoad();

});
