var paymentMethod = localStorage.getItem("payment");
console.log(paymentMethod);

var paymentMethodList = [
    {name: "cash", nameShow: "Trả tiền mặt khi nhận hàng", image: ""},
    {name: "momo", nameShow: "Quét mã MoMo", image: "./assets/images/momo.png"},
    {name: "zalopay", nameShow: "Quét mã ZaloPay", image: "./assets/images/zalopay.png"},
    {name: "bank", nameShow: "Chuyển khoản ngân hàng", image: "./assets/images/bidv.png"}
]

var titleMethod = document.querySelector(".titleMethod");
var textPayMethod = document.querySelectorAll(".paymentMethod");
var imgApp = document.querySelector(".app");
var titleChangeNumber = document.querySelector(".titleChange");
var number = document.querySelector(".titleChange .number");
var qrCode = document.querySelector(".qrcode");
var appName = document.querySelector(".appName");

function init() {
    var methodIndex = 0;
    for (let i = 0; i < paymentMethodList.length; i++) {
        if (paymentMethodList[i].name == paymentMethod) {
            methodIndex = i;
            break;
        }
    }

    if (paymentMethodList[methodIndex].name == "cash") {
        document.querySelector(".pay .pay-show").style.display = "none";
        for (let i = 0; i < textPayMethod.length; i++) {
            textPayMethod[i].textContent = paymentMethodList[methodIndex].nameShow;
        }
    }else if (paymentMethodList[methodIndex].name == "bank") {
        titleMethod.textContent = paymentMethodList[methodIndex].nameShow;
        imgApp.src = paymentMethodList[methodIndex].image;
        imgApp.style.width = "100px";
        titleChangeNumber.innerHTML = `Số tài khoản: <span class="info number">000000000</span>`;
        qrCode.style.display = "none";
        document.querySelector(".pay .sub").style.display = "none";

        for (let i = 0; i < textPayMethod.length; i++) {
            textPayMethod[i].textContent = paymentMethodList[methodIndex].nameShow;
        }
    }else {
        imgApp.src = paymentMethodList[methodIndex].image;
        for (let i = 0; i < textPayMethod.length; i++) {
            textPayMethod[i].textContent = paymentMethodList[methodIndex].nameShow;
        }
        appName.textContent = paymentMethodList[methodIndex].nameShow;
    }
}
init();