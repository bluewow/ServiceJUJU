//========= 글쓰기 ==================
window.addEventListener("load", function () {
	var section = document.querySelector("#stockTop");
	var regBoard = section.querySelector("#reg-button");
	var regBoardForm = document.querySelector(".pop-up-reg");
	var regSubmit = regBoardForm.querySelector(".pop-up-content-row");

	//글쓰기
	regButtonClickHandler = function (e) {

		// 1. 입력한 값을 얻어온다.

		
		var title = regBoardForm.querySelector(".reg-title").value;
		var content = regBoardForm.querySelector(".reg-content").value;
		var boardId = e.target.dataset.id;
		var status = "reg";

		if (boardId != "") {
			status = "modi";
		}

		String.prototype.trim = function () {
			return this.replace(/^\s+|\s+$/g, "");
		}

		var str1 = title;
		str1 = str1.trim();
		var str2 = content;
		str2 = str2.trim();

		alert("aa");
		// 댓글내용이 없으면 알림을 하고 되돌아간다.
		if (str1 == "") {
			alert("제목을 작성하세요");
			return;
		} else if (str2 == "") {
			alert("내용을 작성하세요");
			return;
		} else {



			title = encodeURI(title);
			content = encodeURI(content);

			var data = [
				["boardId", boardId],
				["title", title],
				["content", content],
				["status", status]
			]
			var sendData = [];

			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
			}
			sendData = sendData.join("&");
			console.log(sendData);
			// 2. 값을 서버에 보낸다.

			var request = new XMLHttpRequest();
			request.open("POST", "../../card/board/stock_reg_board", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);

			// 3. 요청이 완료되었는지 결과를 확인한다.
			e.target.parentNode.firstElementChild.value = null;
			e.target.parentNode.parentNode.firstElementChild.firstElementChild.value = null;
			alert("등록되었습니다.");

			load(1);
		}
	};




	//========= 글쓰기 버튼 클릭==================
	regBoard.onclick = function (e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		if (e.target.id == "reg-button") {
			regBoardForm.querySelector(".reg-title").value = null;
			regBoardForm.querySelector(".reg-content").value = null;
			var regBtData = regBoardForm.querySelector(".reg-submit");
			regBtData.dataset.id = "";
			regBoardForm.style.visibility = "visible";
		}
	};

	//========= 글쓰기 취소 버튼 클릭 ==================
	regSubmit.onclick = function (e) {
		if (e.target.nodeName != "INPUT")
			return;

		e.preventDefault();

		if (e.target.name == "cancel") {
			console.log("clicked")
			alert("글등록이 취소되었다!!")
			regBoardForm.style.visibility = "hidden";
			//========= 글쓰기 등록 버튼 클릭==================
		} else if (e.target.name == "submit") {
			console.log("clicked")
			regButtonClickHandler(e);


			regBoardForm.style.visibility = "hidden";
		}
	};


})
