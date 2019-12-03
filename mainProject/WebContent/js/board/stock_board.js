window.addEventListener("load", function() {

	var section = document.querySelector("#stockScroll");
	var tbody = section.querySelector("table tbody");
	var pager = section.querySelector(".pager");

	var load = function(page) {

		var request = new XMLHttpRequest();
		request.open("GET", "../../card/board/stock_board_list?p="
				+ page);

		request.onload = function() {
			var list = JSON.parse(request.responseText);
			var trTemplate = section.querySelector(".tr-template");
			console.log

			tbody.innerHTML = "";

			for (var i = 0; i < list.length; i++) {
				var cloneTr = document.importNode(trTemplate.content,
						true);

				var tds = cloneTr.querySelectorAll("td");
				var title = "[" + list[i].stockName + "] "
						+ list[i].title + "(" + list[i].replyCnt + ")";

				tds[0].innerText = list[i].id;
				var aTagDetail = tds[1].firstElementChild;
				aTagDetail.dataset.id = list[i].id;
				aTagDetail.innerText = list[i].title;
				tds[2].innerText = list[i].regdate;
				tds[3].innerText = list[i].hit;
				var aTagFavo = tds[4].firstElementChild;
				aTagFavo.dataset.id = list[i].id;
				var aTagDel = tds[5].firstElementChild;
				aTagDel.dataset.id = list[i].id;
				tds[6].innerText = list[i].writerId;
				tbody.append(cloneTr);
			}
			;
		};
		request.send();
	}

	load(1)
	pager.onclick = function(e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		load(e.target.innerText);
	};

	tbody.onclick = function(e) {
		if (!e.target.parentNode.classList.contains("board-title"))
			return;
		e.preventDefault();

		var currentTr = e.target.parentNode.parentNode;
		var nextTr = currentTr.nextElementSibling.nextElementSibling;

		if (nextTr.classList.contains("content-row")) {
			alert("이미 있잖아~");
			return;
		}

		if (e.target.parentNode.lastChild.nodeName == "IMG") {
			alert("로딩중입니다~");
			return;
		}
		var id = e.target.dataset.id;

		var ajaxIcon = document.createElement("img");
		ajaxIcon.src = "../../images/delay-icon.gif";
		e.target.parentNode.append(ajaxIcon);

		var request = new XMLHttpRequest();
		request.open("GET", "../../card/board/detail?id=" + id, true);
		request.onload = function() {
			var detail = JSON.parse(request.responseText);
			var template = section.querySelector(".detail-template");
			var cloneTr = document.importNode(template.content, true);
			var td = cloneTr.querySelector(".content-row td");
			td.innerHTML = detail.board.content;
			var replyContent = cloneTr
					.querySelector(".replyTable tbody tr td");
			var contentSum = "";

			for (var i = 0; i < detail.replys.length; i++) {
				contentSum += detail.replys[i].writerId + " : "
						+ detail.replys[i].reContent + "</br>";
			}
			replyContent.innerHTML = contentSum;
			console.log(td);
			console.log(replyContent);

			tbody.insertBefore(cloneTr, nextTr);

			console.log(nextTr);
			ajaxIcon.remove();
			ajaxIcon = undefined;
		};
		request.send();
	};

	function del_row() {
		var stock_tbody_tr = stock_tbody.getElementsByTagName('tr');
		if (stock_tbody_tr.length > 1) {
			stock_tbody.deleteRow(stock_tbody_tr.length - 1);
			stock_y--;
		}
	}
})