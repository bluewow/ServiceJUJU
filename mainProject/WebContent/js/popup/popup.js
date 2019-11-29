window.addEventListener("load", function(){
    var loginBox = document.querySelector(".personal");
    var loginPopup = document.querySelector(".pop-up");

    loginBox.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        e.preventDefault();

        if(e.target.value == "로그인") {
        	loginPopup.style.visibility = "visible";

        } else if(e.target.value == "로그아웃") {
            var request = new XMLHttpRequest();
            request.open("POST", "/login", true);
            request.setRequestHeader("status","LOGOUT");
            request.send();

        } else {
        	//userName
        }
    }
    
});