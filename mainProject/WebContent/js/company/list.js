window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName")
    
    attentionimageControl.onclick = function(){
        var cnTemp = cn.value;
        alert(cnTemp);
        
        var request = new XMLHttpRequest();
        request.open("GET", "../../card/search/search-json?cn="+cn, true);
        
        
        //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    	console.log(request.readyState);
    	request.send();
    };
   
});