// tự chuyển động sale
var items = document.getElementsByClassName("product-item");
var next = document.getElementById("next");
var previous = document.getElementById("previous");

// kích thước 1 item
var itemWidth = items[0].clientWidth + 30;

var step = itemWidth;

var countMax = (items.length * itemWidth) / step;

var count = 0;
var direct = "next";

// Nhấn next
next.addEventListener("click", function () {
    carousel.scrollLeft += step;
    count++;
    if (count == (countMax - 2)) {
        count = 1;
        direct = "previous";
    }
});

// Nhấn previous
previous.addEventListener("click", function () {
    carousel.scrollLeft -= step;
    count++;
    if (count == (countMax - 2)) {
        count = 1;
        direct = "next";
    }
});


// Tự lướt
setInterval(function () {
    count++;
    if (count == (countMax - 2)) {
        count = 1;
        if (direct == "next") {
            direct = "previous";
        } else if (direct == "previous") {
            direct = "next";
        }
    }

    if (direct == "next") {
        carousel.scrollLeft += step;
    } else {
        carousel.scrollLeft -= step;
    }
}, 3000);


var textMin = document.getElementById("text-min");
var textMax = document.getElementById("text-max");
var minValue = 0;
var maxValue = 0;


function changeCurrency(text) {
    text.value = parseFloat(text.value).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
}

function changeCurrent(input) {
    var removeChar = input.value.replace(/[^0-9\.]/g, '')
    var removeDot = removeChar.replace(/\./g, '')
    input.value = removeDot
    var formatNumber = input.value.replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    input.value = formatNumber
}

textMin.addEventListener("input", function () {
    changeCurrent(textMin)
});


changeCurrent(textMin)
changeCurrent(textMax)

textMax.addEventListener("input", function () {
    changeCurrent(textMax)
});



function changeToNumber(currency) {
    return parseInt(currency.substring(0, currency.length - 2).replaceAll(".", ""))
}




// COLOR
var colorItems = document.getElementsByClassName("color-item");
var checkBoxs = document.getElementsByClassName("check-color");

// Đổi màu nền
function changeBG(input) {
    for (var i = 0; i < checkBoxs.length; i++) {
        if (checkBoxs[i] === input) {
            if (input.checked === true) {
                if (input.id == "black" || input.id == "red" || input.id == "blue") {
                    colorItems[i].style.color = "#f3f6fa";
                }
                colorItems[i].style.backgroundColor = input.id;
                return;
            } else {
                if (input.id == "black" || input.id == "red" || input.id == "blue") {
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
            if (checkBoxs[i].id == "black" || checkBoxs[i].id == "red" || checkBoxs[i].id == "blue") {
                colorItems[i].style.color = "#f3f6fa";
            }
            colorItems[i].style.backgroundColor = checkBoxs[i].id
        }
    }
}

colorCheck();


var select = document.querySelector(".sort select");
var hiddenSort = document.querySelector("#hiddenSort");
select.addEventListener("change", function () {
    if (this.value == 1) {
        hiddenSort.value = "";
    } else if (this.value == 2) {
        hiddenSort.value = "asc";
    } else if (this.value == 3) {
        hiddenSort.value = "desc"
    }
    document.querySelector(".filterBtn").click();
})

var selectPage = document.querySelector(".pagination .selectPage select");
var option = document.querySelectorAll(".pagination .selectPage select option")
selectPage.addEventListener("change", function () {
    document.querySelector("#hiddenSelectPage").value = selectPage.value;
    document.querySelector(".filterBtn").click();
});
