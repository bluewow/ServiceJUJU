window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName")
    
    attentionimageControl.onclick = function(){
        var cnTemp = cn.innerText;
        alert(cnTemp);
        
        var request = new XMLHttpRequest();
        request.open("GET", "../../card/company/list-json?cn="+cn, true);
        
        console.log(request.readyState);
    	request.send();
    };
   
});