var paymentChecksShow = document.querySelectorAll(".payment .payment-item input.show");
var paymentChecksHide = document.querySelectorAll(".payment .payment-item input.hide");

function checkPayment() {
    for (let i = 0; i < paymentChecksHide.length; i++) {
        paymentChecksHide[i].addEventListener("click", function () {
            for (let j = 0; j < paymentChecksHide.length; j++) {
                paymentChecksShow[i].checked = "false";
                paymentChecksHide[i].checked = "false";
            }
            paymentChecksShow[i].checked = "true";
        });
    }
}

checkPayment();


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