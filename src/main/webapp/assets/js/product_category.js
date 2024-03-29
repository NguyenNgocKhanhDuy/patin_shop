
var textMin = document.getElementById("text-min");
var textMax = document.getElementById("text-max");
var minValue = 10000;
var maxvalue = 10000000;

function changeCurrency() {
    textMin.value = parseFloat(textMin.value).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
    textMax.value = parseFloat(textMax.value).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });

}

changeCurrency();


textMin.addEventListener("blur", function () {
    var value = this.value.replace(/,/g, '')
    if (this.value < minValue || this.value > changeToNumber(textMax.value)){
        value = minValue;
    }
    this.value = parseFloat(value).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
});


function changeToNumber(currency) {
    return parseInt(currency.substring(0, currency.length - 2).replaceAll(".", ""))
}


textMin.addEventListener("focus", function () {
    textMin.value = changeToNumber(textMin.value);
});

var oldMin = minValue;
textMin.addEventListener("keydown", function (){
    oldMin = this.value;
})



textMax.addEventListener("blur", function () {
    var value = this.value.replace(/,/g, '')
    if (changeToNumber(textMin.value) > this.value || this.value > maxvalue){
        value = maxvalue;
    }
    this.value = parseFloat(value).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
});

textMax.addEventListener("focus", function (){
    textMax.value = changeToNumber(textMax.value);
});

var oldMax = maxvalue;
textMax.addEventListener("keydown", function (){
    oldMax = this.value;

})




// COLOR
var colorItems = document.getElementsByClassName("color-item");
var checkBoxs = document.getElementsByClassName("check-color");

// Đổi màu nền
function changeBG(input) {
    for(var i = 0; i < checkBoxs.length; i++) {
        if(checkBoxs[i] === input){
            if(input.checked === true){
                if(input.id == "black" || input.id == "red" || input.id == "blue"){
                    colorItems[i].style.color = "#f3f6fa";
                }
                colorItems[i].style.backgroundColor = input.id;
                return;
            }else{
                if(input.id == "black" || input.id == "red" || input.id == "blue"){
                    colorItems[i].style.color = "black";
                }
                colorItems[i].style.backgroundColor = "#f3f6fa";
            }
        }
    }
}

function colorCheck() {
    for (let i = 0; i < checkBoxs.length; i++) {
        if (checkBoxs[i].checked === true) {
            if(checkBoxs[i].id == "black" || checkBoxs[i].id == "red" || checkBoxs[i].id == "blue"){
                colorItems[i].style.color = "#f3f6fa";
            }
            colorItems[i].style.backgroundColor = checkBoxs[i].id
        }
    }
}
colorCheck();


var select = document.querySelector(".sort select");
var hiddenSort = document.querySelector("#hiddenSort");
select.addEventListener("change", function (){
    if (this.value == 1){
        hiddenSort.value = "";
    }else if (this.value == 2){
        hiddenSort.value = "asc";
    }else if (this.value == 3){
        hiddenSort.value = "desc"
    }
    document.querySelector(".filterBtn").click();
})

var popup = document.querySelector(".popup");

var del = document.querySelector(".popup .del");


function hideError() {
    popup.classList.add("fadeOut")
}

if (popup.style.opacity != "0") {
    setTimeout(hideError, 3000);
}

del.addEventListener("click", hideError);