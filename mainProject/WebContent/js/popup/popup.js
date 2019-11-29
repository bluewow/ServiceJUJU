window.addEventListener("load", function(){
    var loginBox = document.querySelector(".personal");
    var wrapper = document.querySelector(".pop-up-wrapper");
    var loginPopup = document.querySelector(".pop-up");

    window.onclick = function(e) {
        if (e.target == wrapper) {
            wrapper.style.visibility = "hidden";
            loginPopup.style.visibility = "hidden";
        }
    }

    loginBox.onclick = function(e) {
    	//로그인, 로그아웃, UserName filter
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();

        if(e.target.value == "로그인") {
        	//로그인 창 및 background color visible
        	wrapper.style.visibility = "visible";
            loginPopup.style.visibility = "visible";

        } else if(e.target.value == "로그아웃") {
        	//로그아웃시 session 만료
            var request = new XMLHttpRequest();
            request.open("POST", "/login", true);
            request.setRequestHeader("status","LOGOUT");
            request.send();

        } else {
        	//userName
        }
    }
    
});