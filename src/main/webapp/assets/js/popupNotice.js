
var popup = document.querySelector(".popup");

var del = document.querySelector(".popup .del");


function hideError() {
    popup.classList.add("fadeOut")
    setTimeout(hide, 500);
}

if (popup.style.opacity != "0") {
    setTimeout(hideError, 3000);
}

function hide() {
    popup.classList.remove("error", "alert", "success")
    popup.classList.add("none")
}

del.addEventListener("click", hideError);