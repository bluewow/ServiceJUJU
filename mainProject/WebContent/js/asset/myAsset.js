// 해브스톡리스트 추가하기
window.addEventListener("load",function(){
    var section = this.document.querySelector("#card");
    var tbody = section.querySelector("table tbody");
    var updateButton = section.querySelector("#card-bottom");

    var load = function(){
        var request = new XMLHttpRequest();
        request.open("GET","../../card/asset/myAsset-json",true);

        request.onload = function(){

            var distrListTemplate = section.querySelector(".template-list-stock");
            var list = JSON.parse(request.responseText);
            alert(list[0].distJson[0].stockName);

            tbody.innerHTML = "";

            for(var i=0; i<list[0].distJson.length; i++){
                var distrImage = '<img src="../../images/distr_list_0'+((i%4)+1)+'.png">';
                alert(distrImage);

                var cloneTr = document.importNode(distrListTemplate.content, true);
                var tds = cloneTr.querySelectorAll("td");

                tds[0].innerHTML = distrImage;
                tds[1].innerText = list[0].distJson[i].stockName;

                tbody.append(cloneTr);
            }
            console.log(tbody.innerHTML);
        };
        request.send();
    };
    load();
});