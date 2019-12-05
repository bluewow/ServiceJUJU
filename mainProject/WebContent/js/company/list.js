window.addEventListener("load", function(){
    var attentionimageControl = document.querySelector(".attention");
    var cn = document.querySelector("#companyName")
    var CompanyNameClickedByUser = document.querySelector("#companyName")
    
    CompanyNameClickedByUser.onclick = function(e){
        e.preventDefault();
        alert("preventDefault 됬나?")
        console.log(e.currentTarget.tagName);
    };

    /*attentionimageControl.onclick = function(){
        var cnTemp = cn.innerText;
        alert(cnTemp);
        
        var request = new XMLHttpRequest();
        request.open("GET", "../../card/company/list-json?cn="+cn, true);
        
        console.log(request.readyState);
    	request.send();
    };*/
   
});