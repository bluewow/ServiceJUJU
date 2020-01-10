window.addEventListener("load", function(){
	
	loginFunc();	//로그인 팝업
	buttonFunc();	//로그인/로그아웃/프로필 버튼 
	hiddenFunc();	//팝업 hidden
	signUpFunc();	//회원가입 팝업
	profileFunc();	//프로필설정 팝업
	
	////////////////////////////
	//로그인 팝업 
	////////////////////////////
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
//	        	document.querySelector("#login").submit();

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
	
	////////////////////////////
	//로그인/로그아웃/프로필 버튼 관련 함수
	////////////////////////////
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
	            request.onload = function() {
	                location.reload(true);
	            }
	            request.send();

	        } else {
	            //프로필
	             var profilePopup = document.querySelector(".profile-pop-up");
	             wrapper.style.visibility = "visible";
	             profilePopup.style.visibility = "visible";
	        }
	    }
	}
	
	
	////////////////////////////
	//팝업 hidden 처리
	////////////////////////////
	function hiddenFunc() {
	    window.onmousedown = function(e) {
	        var wrapper = document.querySelector(".pop-up-wrapper");
	        var loginPopup = document.querySelector(".pop-up");
	        var signupPopup = document.querySelector(".sign-up-pop-up");
		    var profileImage = document.querySelector(".pop-up-profile-image");
		    var profilePopup = document.querySelector(".profile-pop-up");
	        if (e.target == wrapper) {
	            wrapper.style.visibility = "hidden";
	            loginPopup.style.visibility = "hidden";
	            signupPopup.style.visibility = "hidden";
	            profilePopup.style.visibility = "hidden";
	            profileImage.style.visibility = "hidden";
	        }
	    }
	}
	
	////////////////////////////
	//회원가입 팝업
	////////////////////////////
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
//	            var form = signupPopup.querySelector(".login-box");
	        	document.querySelector("#signup").submit();

//	        	form.submit();
	        }
	    }
	}

	////////////////////////////
	//프로필설정 팝업
	///////////////////////////
	function profileFunc() {
	
	    var profilePopup = document.querySelector(".profile-pop-up");
	    var profileImage = profilePopup.querySelector(".pop-up-top-image");	
	    var sectionImg = document.querySelector(".pop-up-profile-image");
	    var profileImageList = sectionImg.querySelector(".profile-image-list");
        var submitButton = profilePopup.querySelector(".login-box");
        
        var imgSelectButton = sectionImg.querySelector(".profile-img-select-submit");
        
    	var profileImg = profilePopup.querySelector(".profile-photo-modi");
    	var currentPwd = profilePopup.querySelector(".currentPwd");
    	var newPwd = profilePopup.querySelector(".newPwd");
    	var checkPwd = profilePopup.querySelector(".checkPwd");
	    console.log(submitButton)
	    var currentSelect;
	    //프로필 이미지 클릭 시
	    profileImage.onclick = function(e) {
	        if(e.target.nodeName != "IMG")
	            return;

	        //prevent Event Bubble
	        e.preventDefault();
	        var nowImg = e.target.dataset.id;
	        sectionImg.style.visibility = "visible";
	        var list = "";
	        	for(var i=1; i<=36; i++) {
	        		if(i==nowImg) 
		        		var photos =  `<img src="/images/profile/${i}.png" 
			        	alt="profile photo" class="images image-selected"
			        	 data-id="${i}">`
	        		else if(i!=nowImg) 
		        		var photos =  `<img src="/images/profile/${i}.png" 
			        	alt="profile photo" class="images"
			        	 data-id="${i}">`
	        	        list = list + photos;
	        	}

	        profileImageList.innerHTML = list;
	        currentSelect = profileImageList.getElementsByClassName("image-selected")[0];
	    }
	    
	    //확인버튼 클릭 시
	    submitButton.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();
        
        //데이터 준비
    	var data = [
			["currentPwd", currentPwd.value],
			["newPwd", newPwd.value]
			]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
			}
		
		sendData = sendData.join("&");

		//데이터 전송
		console.log(sendData)
		var request = new XMLHttpRequest();
		request.open("POST", "../../member-profile", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);	
			
	    }

	    //프로필 이미지 리스트중 하나를 클릭했을 시
	    profileImageList.onclick = function(e) {
	        if(e.target.nodeName != "IMG")
	            return;
	        e.preventDefault();
	        
	        currentSelect.classList.remove("image-selected");
	        e.target.classList.add("image-selected");
	        currentSelect = profileImageList.getElementsByClassName("image-selected")[0];
	    }
	    
	    //프로필 이미지 선택 후 확인버튼 클릭시
        imgSelectButton.onclick = function(e) {
	        if(e.target.nodeName != "INPUT")
	            return;
	        e.preventDefault();
	        var selectPhoto = currentSelect.dataset.id;
	        var changePhoto = profileImage.getElementsByClassName("profile-photo-modi")[0];
	        changePhoto.parentNode.innerHTML = 
	        	`<img src="/images/profile/${selectPhoto}.png" 
	        	alt="profile photo" class="circle float-left profile-photo-modi"
	        	data-id="${selectPhoto}">`;
	        //데이터 준비
	    	var data = [
				["profileImg", selectPhoto]
				]
			var sendData = [];

			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
				}
			
			sendData = sendData.join("&");

			//데이터 전송
			console.log(sendData)
			//데이터 전송
			var request = new XMLHttpRequest();
			request.open("POST", "../../member-profile", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);	
			
			request.onload = function () {
				var lastReplyNum = request.responseText;
				alert("등록되었습니다."+lastReplyNum);
				sectionImg.style.visibility = "hidden";
			}
	        
        }
	    
	}
});






