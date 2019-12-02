//로그인 팝업 
window.addEventListener("load", loginFunc);
//로그인/로그아웃/프로필 버튼 
window.addEventListener("load", buttonFunc);
//팝업 hidden
window.addEventListener("load", hiddenFunc);
//회원가입 팝업
window.addEventListener("load", signUpFunc);
//프로필설정 팝업
window.addEventListener("load", profileFunc);

//로그인 팝업 
function loginFunc() {
    var loginPopup = document.querySelector(".pop-up");
    var signupOn = document.querySelector(".sign-up-pop-up");

    loginPopup.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();

        if(e.target.value =="로그인") {
            //TODO 이메일 양식 비밀번호 체크
        	var form = loginPopup.querySelector(".login-box");
//        	document.querySelector("#login").submit();

        	form.submit();
            
        }
        else if(e.target.value == "무료회원가입") {
            loginPopup.style.visibility = "hidden";
            signupOn.style.visibility = "visible";

        } else if(e.target.value == "이메일/비밀번호 찾기") {
            alert("이메일");
        } 
    }
}

//로그인/로그아웃/프로필 버튼 관련 함수
function buttonFunc() {
    var popupOn = document.querySelector(".personal");
    var wrapper = document.querySelector(".pop-up-wrapper");

    popupOn.onclick = function(e) {
        //로그인, 로그아웃, UserName filter
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();

        if(e.target.value == "로그인") {
            //로그인 창 및 background color visible
            var loginPopup = document.querySelector(".pop-up");
            wrapper.style.visibility = "visible";
            loginPopup.style.visibility = "visible";

        } else if(e.target.value == "로그아웃") {
            //로그아웃시 session 만료
            var request = new XMLHttpRequest();
            request.open("GET", "/login?loginStatus=logout");
            request.send();
            request.onload = function() {
                location.reload(true);
            }

        } else {
            //프로필
             var profilePopup = document.querySelector(".profile-pop-up");
             wrapper.style.visibility = "visible";
             profilePopup.style.visibility = "visible";
        }
    }
}

//팝업 hidden 처리
function hiddenFunc() {
    window.onclick = function(e) {
        var wrapper = document.querySelector(".pop-up-wrapper");
        var loginPopup = document.querySelector(".pop-up");
        var signupPopup = document.querySelector(".sign-up-pop-up");
        var profilePopup = document.querySelector(".profile-pop-up");

        if (e.target == wrapper) {
            wrapper.style.visibility = "hidden";
            loginPopup.style.visibility = "hidden";
            signupPopup.style.visibility = "hidden";
            profilePopup.style.visibility = "hidden";
        }
    }
}

//회원가입 팝업
function signUpFunc() {
    var signupPopup = document.querySelector(".sign-up-pop-up");

    signupPopup.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();

        if(e.target.nodeName == "userEmail") {
            alert("TEST");
        } else if(e.target.nodeName == "nickName") {

        } else if(e.target.nodeName == "pwd") {
        
        } else if(e.target.nodeName == "checkPwd") {
        
        } else if(e.target.value == "회원가입") {
//            var form = signupPopup.querySelector(".login-box");
        	document.querySelector("#signup").submit();

//        	form.submit();
        }
    }
}

//프로필설정 팝업
function profileFunc() {

}