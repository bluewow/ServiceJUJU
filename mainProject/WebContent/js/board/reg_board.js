//========= 글쓰기 ==================
window.addEventListener("load", function() {
	var section = document.querySelector("#stockTop");
	var regBoard = section.querySelector("#reg-button");
	var regBoardForm = document.querySelector(".pop-up-reg");
	var regSubmit = regBoardForm.querySelector(".pop-up-content-row");
//========= 글쓰기 ==================
	regBoard.onclick = function(e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		if (e.target.id == "reg-button")
			regBoardForm.style.visibility = "visible";
	};
	//========= 글쓰기 등록 ==================

	//========= 글쓰기 취소 ==================
	regSubmit.onclick = function(e) {
		if (e.target.nodeName != "INPUT")
			return;

		e.preventDefault();

		if (e.target.name == "cancel")
			regBoardForm.style.visibility = "hidden";
		else if (e.target.name == "submit")
			alert("글등록을 거부한다!!")
			regBoardForm.style.visibility = "hidden";
	};
})
