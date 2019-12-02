window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName")
    
    attentionimageControl.onclick = function(){
        alert("이미지 클릭 구현중입니다...........................");
        
        var request = new XMLHttpRequest();
        request.open("GET", "../../card/search/search-json?cn="+cn, true);
        
        
        //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    	console.log(request.readyState);
    	request.send();
    };
   
});