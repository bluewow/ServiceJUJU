//로그인 팝업 
window.addEventListener("load", loginFunc);
//로그인/로그아웃/회원 버튼 
window.addEventListener("load", buttonFunc);
//팝업 hidden
window.addEventListener("load", hiddenFunc);
//회원가입 팝업
window.addEventListener("load", signUpFunc);

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
        	var form = document.querySelector(".login-box");
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

//로그인/로그아웃/회원 버튼 관련 함수
function buttonFunc() {
    var popupOn = document.querySelector(".personal");
    var wrapper = document.querySelector(".pop-up-wrapper");
    var loginPopup = document.querySelector(".pop-up");

    popupOn.onclick = function(e) {
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
            request.open("GET", "/login?loginStatus=logout");
            request.send();
            request.onload = function() {
                location.reload(true);
            }

        } else {
            //회원
        }
    }
}

//팝업 hidden 처리
function hiddenFunc() {
    window.onclick = function(e) {
        var wrapper = document.querySelector(".pop-up-wrapper");
        var loginPopup = document.querySelector(".pop-up");
        var signupPopup = document.querySelector(".sign-up-pop-up");

        if (e.target == wrapper) {
            wrapper.style.visibility = "hidden";
            loginPopup.style.visibility = "hidden";
            signupPopup.style.visibility = "hidden";
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

        alert("회원가입");
    }
}