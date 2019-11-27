window.addEventListener("load", function () {
    var section = document.querySelector("#stockScroll");
    var myButton = section.querySelector("#my-button");
    var favoButton = section.querySelector("#favo-button");
    var delButton = section.querySelectorAll("#del-button");
    var favoCheck = section.querySelector("#favo-check");
    var boardTitle = section.querySelector("#board-title");
    
    var arr = [];

    myButton.onclick = function () {
    	alert("my-button")
    }

    favoButton.onclick = function () {
    	alert("favo-button")
    }

    delButton.onclick = function () {
    	alert("del-button")
    }

    favoCheck.onclick = function () {
    	alert("favo-check")
    }

    boardTitle.onclick = function () {
    	alert("board-title")
    }

    var modal = document.querySelector(".modal"); 
    var trigger = document.querySelector(".trigger"); 
    var closeButton = document.querySelector(".close-button"); 
    var cancelButton = document.querySelector("#cancel");

   //console.log(modal);

   function toggleModal() { 
        modal.classList.toggle("show-modal"); 
    }

   function windowOnClick(event) { 
        if (event.target === modal) { 
            toggleModal(); 
        } 
    }

   trigger.addEventListener("click", toggleModal); 
    closeButton.addEventListener("click", toggleModal); 
    cancel.addEventListener("click", toggleModal); 
    window.addEventListener("click", windowOnClick); 


})
