window.addEventListener("load", function(){
    var mainSection = document.querySelector("#main-content");
    var cards = mainSection.querySelectorAll(".card");
    var currentCard;
    
    for (var i = 0; i < cards.length; i++){
        cards[i].draggable = true;    
    }    
    
    mainSection.ondragstart = function(e){
        currentCard = e.target;
        
        if (!currentCard.classList.contains("card"))
        return;
    }

    mainSection.ondragover = function(e){
        e.preventDefault();
    }

    mainSection.ondrop = function(e){
        var targetCard = e.target.parentNode.parentNode.parentNode;
        
        if (!targetCard.classList.contains("card"))
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