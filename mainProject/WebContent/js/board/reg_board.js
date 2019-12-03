window.addEventListener("load", function() {
	var section = document.querySelector("#stockTop");
	var regBoard = section.querySelector("#reg-button");
	var regBoardForm = document.querySelector(".pop-up-reg")
	
	regBoard.onclick = function (e) {
        if (e.target.nodeName != "A")
            return;

        e.preventDefault();
    };
	
})