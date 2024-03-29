var goTop = document.querySelector(".goTop");
function showGoTop() {
    var windowHeight = window.innerHeight;
    var heightShow = document.querySelector("#content").getBoundingClientRect().top;
    if (windowHeight > (heightShow + 500)){
        goTop.classList.add("showGoTop");
    }else {
        goTop.classList.remove("showGoTop");
    }
}
window.addEventListener("scroll", showGoTop)