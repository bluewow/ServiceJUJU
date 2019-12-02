window.addEventListener("load", function () {
	alert("hello")
	
	var section =  document.querySelector("#detail");
	var tbody = section.querySelector("table tbody");
	
	
	var request = new XMLHttpRequest();
	request.open("GET", "../../card/board/detail?id=6");
	request.onload = function() {
		var detail = JSON.parse(request.responseText);
		console.log(detail)
	}
	
	
//	
//	var load = function() {
//
//		var request = new XMLHttpRequest();
//		request.open("GET", "../../card/board/detail?id=" + id);
//
//		request.onload = function() {
//			var list = JSON.parse(request.responseText);
//			var trTemplate = section.querySelector(".tr-template");
//
//			tbody.innerHTML = "";
//
//			for (var i = 0; i < list.length; i++) {
//				var cloneTr = document.importNode(trTemplate.content, true);
//
//				var tds = cloneTr.querySelectorAll("td");
//				var title = "[" + list[i].stockName + "] " + list[i].title
//						+ "(" + list[i].replyCnt + ")";
//				
//				tds[0].innerText = list[i].id;
//				tds[1].innerHTML = '<a href=\"detail?id='+list[i].id+'\">'+title+'</a>';
//				tds[2].innerText = list[i].regdate;
//				tds[3].innerText = list[i].hit;
//				tds[4].innerHTML = '<a href=\"favo-add?id='+list[i].id+'\">'+list[i].id+'</a>';
//				tds[5].innerHTML = '<a href=\"del?id='+list[i].id+'\">'+list[i].id+'</a>';
//				tds[6].innerText = list[i].writerId;
//				tbody.append(cloneTr);
//			};
//		};
//		request.send();
//	}

//	load(1);
//    var load = function(page) {
//    	
//        var request = new XMLHttpRequest();
//        request.open("GET", "../../card/board/detail?id=" + page);
//
//
//        request.onload = function() {
//        var list = JSON.parse(request.responseText);
//        var trTemplate = section.querySelector(".tr-template");
//
//        tbody.innerHTML = "";
//
//        for (var i = 0; i < list.length; i++) {
//            var cloneTr = document.importNode(trTemplate.content, true);
//
//            var tds = cloneTr.querySelectorAll("td");
//            tds[0].innerText = list[i].id;
//            var title = "["+list[i].stockName+"] "+list[i].title+"("+list[i].replyCnt+")";
//            tds[1].firstElementChild.innerText = title;
//            tds[2].innerText = list[i].regdate;
//            tds[3].innerText = list[i].hit;
//            tds[4].firstElementChild.innerText = list[i].stockName;
//            tds[5].firstElementChild.innerText = list[i].id;
//            tds[6].innerText = list[i].writerId;
//            tbody.append(cloneTr);
//            console.log(title);
//        };
//        
//    };
//    
//    request.send();
//    
//    }
//    
//    load(1);
//    
//    pager.onclick = function (e) {
//        if (e.target.nodeName != "A") 
//            return;
//            
//       e.preventDefault();
//       load(e.target.innerText);
//        };
	
	//communityBoardFrame.onload = function(){		
		
	//};		
	
	

//window.addEventListener("load", function () {
//    var section = document.querySelector("#communityScroll");
//    var myButton = section.querySelector("#my-button");
//    var favoButton = section.querySelector("#favo-button");
//    var delButton = section.querySelectorAll("#del-button");
//    var favoCheck = section.querySelector("#favo-check");
//    var boardTitle = section.querySelector("#board-title");
//    
//    var arr = [];
//
//    myButton.onclick = function () {
//    	alert("my-button")
//    }
//
//    favoButton.onclick = function () {
//    	alert("favo-button")
//    }
//
//    delButton.onclick = function () {
//    	alert("del-button")
//    }
//
//    favoCheck.onclick = function () {
//    	alert("favo-check")
//    }
//
//    boardTitle.onclick = function () {
//    	alert("board-title")
//    }
//    
})