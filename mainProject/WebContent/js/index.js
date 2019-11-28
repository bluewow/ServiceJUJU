window.addEventListener("scroll", function(){
    
});

window.addEventListener("resize", function(){
    resizeDiv();
});

window.addEventListener("load", function() {
    resizeDiv();
    
    var navigation = document.querySelector("#index-navigator");
    var circles = navigation.querySelectorAll(".circle");
    for(var i = 0; i < circles.length; i++)
        circles[i].style.top = (i * 25) + "px";

    navigation.onclick = function(e) {
        var clickCircle = e.target;

        if(clickCircle.classList[0] == "circle") {
            var body = document.querySelector("body");
            var circleTop = window.getComputedStyle(clickCircle).top;
            var circleIndex = parseInt(circleTop) / 25;
            var stage = document.querySelectorAll(".page")[circleIndex];
            var offset = stage.offsetTop;
            
            window.scroll({
                behavior: 'smooth',
                left: 0,
                top: offset
            });
        }
    }

});

function resizeDiv() {
    var pages = document.querySelectorAll(".page");

    for(var i=0;i<pages.length;i++)
        pages[i].style.height = this.innerHeight + "px";
}