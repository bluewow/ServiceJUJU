// 해브스톡리스트 추가하기
window.addEventListener("load",function(){

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(load);

    var section = this.document.querySelector("#card");
    var tbody = section.querySelector("table tbody");
    var updateButton = section.querySelector("#card-bottom");

    var load = function(){
        var request = new XMLHttpRequest();
        request.open("GET","../../card/asset/myAsset-json",true);

        request.onload = function(){
        	//alert(request.responseText);

            var distrListTemplate = section.querySelector(".template-list-stock");
            var list = JSON.parse(request.responseText);

            tbody.innerHTML = "";

            for(var i=0; i<list[0].distJson.length; i++){
                var distrImage = '<img src="../../images/distr_list_0'+((i%4)+1)+'.png">';

                var cloneTr = document.importNode(distrListTemplate.content, true);
                var tds = cloneTr.querySelectorAll("td");

                tds[0].innerHTML = distrImage;
                tds[1].innerText = list[0].distJson[i].stockName;

                tbody.append(cloneTr);
            }

            var data = new google.visualization.DataTable();
            data.addColumn('string', 'stockName');
            data.addColumn('number', 'ratio');
            data.addColumn({type:'string', role:'tooltip'});
            
            function formatNumber(num) {
              return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
            }
            for(var i=0; i<list[0].distJson.length; i++){

              var stockName = list[0].distJson[i].stockName;
              var assetValue = formatNumber(list[0].distJson[i].assetValue);
              var ratio = list[0].distJson[i].ratio;
              
              data.addRows([
                [stockName,ratio,stockName+": "+assetValue+"원"]
              ]);
            }

            var options = {
              pieHole: 0.45,
              pieSliceTextStyle: {
                color: 'black',
              },
              pieSliceBorderColor: "none",
              backgroundColor:"none",
              colors: ['#F2F2F2', '#BF737C', '#689ABC', '#585B5E'],
              legend: 'none',
              pieSliceText: "value"
            };

            var formatter = new google.visualization.NumberFormat({pattern: '#.#'});
            formatter.format(data,1);

              var chart = new google.visualization.PieChart(document.getElementById('donut_single'));
              chart.draw(data, options);
                         	  
        };
        request.send();
    };
    load();
    
});
