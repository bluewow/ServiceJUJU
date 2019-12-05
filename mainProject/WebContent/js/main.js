window.addEventListener("load", function() {
	var stage = document.querySelector("#dragdiv");
	var currentCard;

	function load(e) {
		var request = new XMLHttpRequest();

		request.open("GET", "../main-json", true);

		request.onload = function() {
			var list = JSON.parse(request.responseText);
			list = list.split(",");

			var cardTemplate = document.querySelector(".cards-template");
			var cloneCards = document.importNode(cardTemplate.content, true);
			var cards = cloneCards.querySelectorAll(".card");
			var cardsPos = cloneCards.querySelectorAll(".column");
			var cardsCopy = [];

			for (var i = 0; i < cards.length; i++) {
				cardsCopy[i] = cards[i].cloneNode(true);
				cardsPos[i] = cards[i].parentNode;
				cards[i].remove();
			}

			for (var i = 0; i < list.length; i++) {
				var cardIndex = list[i] - 1;

				if (cardIndex == -1)
					continue;

				cardsPos[i].append(cardsCopy[cardIndex])
			}
			stage.append(cloneCards);
			allowDragable();
		};

		request.send();
	}

	load();

	function allowDragable() {
		var cards = stage.querySelectorAll(".card");
		for (var i = 0; i < cards.length; i++) {
			cards[i].draggable = true;
		}
	}

	stage.ondragstart = function(e) {
		currentCard = e.target;
	}

	stage.ondragover = function(e) {
		e.preventDefault();
	}

	stage.ondrop = function(e) {
		var targetCard = e.target.parentNode.parentNode.parentNode;

		if (!targetCard.classList.contains("card"))
			return;

		if (targetCard == currentCard)
			return;

		if (currentCard.nodeName == "A")
			return;

		e.preventDefault();

		var tempCard = targetCard.cloneNode(true);
		var currentCardPos = currentCard.parentNode;
		var targetCardPos = targetCard.parentNode;

		targetCard.remove();
		targetCardPos.append(currentCard);
		currentCardPos.append(tempCard);
	}
});