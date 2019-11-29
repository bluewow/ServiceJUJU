window.addEventListener("load", function(){
    var login = document.querySelector(".personal");
    var popup = document.querySelector(".pop-up");
    
    login.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        e.preventDefault();

        if(e.target.value == "로그인") {
            popup.style.visibility = "visible";

        } else if(e.target.value == "로그아웃") {
            var request = new XMLHttpRequest();
            request.open("POST", "/login", true);
            request.setRequestHeader("status","LOGOUT");
            request.send();

        } else {
        	
        }
    }
});