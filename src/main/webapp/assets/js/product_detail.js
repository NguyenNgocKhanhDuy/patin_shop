// nhấn vào ảnh lớn
var imgMain = document.getElementById("img-main");
var listImg = document.getElementsByClassName("sub-img");
var imgModal = document.getElementById("imageInModal");
var modalImgClick = document.querySelector(".modal-img");
var closeModalImg = document.getElementById("closeModalImg");
var modalImgContainer = document.querySelector(".modal-img-container");
var nextImg = document.getElementById("nextImg");
var previousImg = document.getElementById("previousImg");

function changeImgMain() {
    for(var i = 0; i < listImg.length; i++) {
        listImg[i].addEventListener("click", function () {
            imgMain.src = this.src;
        });
    }
}

changeImgMain();

imgMain.addEventListener("click", function () {
    imgModal.src = this.src;
    modalImgClick.style.display = "flex";
});

closeModalImg.addEventListener("click", function () {
    modalImgClick.style.display = "none";
});

modalImgClick.addEventListener("click", function () {
    modalImgClick.style.display = "none";
});

modalImgContainer.addEventListener("click", function () {
    event.stopPropagation();
});

function chageImg() {
    var index = 0;
    for(var i = 0; i < listImg.length; i++) {
        if (imgModal.src == listImg[i].src){
            index = i;
        }
    }

    if (event.target.id == "nextImg") {
        if(index == listImg.length-1) {
            index = 0;
        }else {
            index++;
        }
        imgModal.src = listImg[index].src;
    }

    if (event.target.id == "previousImg") {
        if(index == 0) {
            index = listImg.length-1;
        }else {
            index--;
        }
        imgModal.src = listImg[index].src;
    }
}

nextImg.addEventListener("click", chageImg);
previousImg.addEventListener("click", chageImg);

var colorChecks = document.querySelectorAll(".check-color input[type='radio']");
var colorHidden = document.querySelectorAll(".check-color input[type='hidden']");
var boxColor = document.querySelectorAll(".check-color");
var deselectBtn = document.querySelector(".deselect");
var boxSizes = document.querySelectorAll(".check-size");
var sizeChecks = document.querySelectorAll(".check-size input");
var countColor = 0;
var countSize = 0;
var colorIndex = 0;
var sizeIndex = 0;



function setInputColor() {
    for (let i = 0; i < colorChecks.length; i++) {
        boxColor[i].style.backgroundColor = colorChecks[i].value;
    }
}

setInputColor();

function select() {
    for(let i  = 0; i < colorChecks.length; i++){
        colorChecks[i].addEventListener("click", function () {
            for(let j = 0; j < boxColor.length; j++) {
                boxColor[j].classList.remove("selectActive");
                colorHidden[j].name = ""
            }
            countColor = 1;
            colorIndex = i;
            colorHidden[i].name = "colors"
            boxColor[i].classList.add("selectActive");
            deselectBtn.style.visibility = "visible";
            if (countSize == 1 && countColor == 1) {
                showPriceQuantity();
            }
        });

        deselectBtn.addEventListener("click", function (){
            countColor = 0;
            countSize = 0;
            colorIndex = 0;
            sizeIndex = 0;
            boxColor[i].classList.remove("selectActive");
            deselectBtn.style.visibility = "hidden";
            priceDetail.textContent = originPrice;
            quantityDetail.textContent = originQuantity;
            enabled();
        });
    }

    for (let i = 0; i < sizeChecks.length; i++) {
        sizeChecks[i].addEventListener("click", function () {
            for (let j = 0; j < boxSizes.length; j++) {
                boxSizes[j].classList.remove("selectActive");
            }
            countSize = 1;
            sizeIndex = i;
            boxSizes[i].classList.add("selectActive");
            deselectBtn.style.visibility = "visible";
            if (countSize == 1 && countColor == 1) {
                showPriceQuantity();
            }
        });



        deselectBtn.addEventListener("click", function (){
            countColor = 0;
            countSize = 0;
            sizeIndex = 0;
            colorIndex = 0;
            boxSizes[i].classList.remove("selectActive");
            deselectBtn.style.visibility = "hidden";
            priceDetail.textContent = originPrice;
            quantityDetail.textContent = originQuantity;
            enabled();
        });
    }

}
select();

var idProduct = document.querySelector("#idProduct");
var priceDetail = document.querySelector("#priceDetail");
var originPrice = priceDetail.textContent;
var quantityDetail = document.querySelector("#quantityDetail");
var originQuantity = quantityDetail.textContent;
function showPriceQuantity() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "productPriceQuantity?id="+idProduct.value+"&size="+sizeChecks[sizeIndex].value+"&color="+colorHidden[colorIndex].value, true);
    xhttp.responseType = 'json'
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var c= {}
            c = xhttp.response
            priceDetail.textContent = changeCurrency(c[0]);
            quantityDetail.textContent = c[1];
            if (c[1] == 0) {
                disabled();
                priceDetail.textContent = originPrice;
            }else {
                enabled();
            }
        }
    }
}

var boxQuantity = document.querySelector(".buy .quantity");
var minus = document.getElementById("minus");
var plus = document.getElementById("plus");
var inputQuantity = document.querySelector(".quantity input");

minus.addEventListener("click", function () {
    var value = parseInt(inputQuantity.value);
    if (value > 1) {
        value--;
    }
    inputQuantity.value = value;
});

plus.addEventListener("click", function () {
    var value = parseInt(inputQuantity.value);
    if (value < parseInt(quantityDetail.textContent)) {
        value++;
    }
    inputQuantity.value = value;
});

var addToCart = document.querySelector(".addTocart");
var addToCartHover = document.querySelector(".addTocart .btnAdd");

function disabled() {
    addToCart.classList.add("disabled");
    addToCartHover.classList.add("disabledBg");
    boxQuantity.classList.add("disabled");
    minus.classList.add("disabledNoBg");
    plus.classList.add("disabledNoBg");
    inputQuantity.classList.add("disabledNoBg");
}

function enabled() {
    addToCart.classList.remove("disabled");
    addToCartHover.classList.remove("disabledBg");
    boxQuantity.classList.remove("disabled");
    minus.classList.remove("disabledNoBg");
    plus.classList.remove("disabledNoBg");
    inputQuantity.classList.remove("disabledNoBg");
}

function changeCurrency(price) {
    return parseFloat(price).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });

}

// rating star
var stars = document.querySelectorAll(".rate i");
for (let i = 0; i < stars.length; i++) {
    stars[i].addEventListener("click", function () {
        document.querySelector("#ratePoint").value = (i+1);
        for (let j = 0; j < stars.length; j++) {
            if(i >= j) {
                stars[j].classList.add("starActive");
            }else {
                stars[j].classList.remove("starActive");
            }
        }
    });
}


var selectPage = document.querySelector(".pagination .selectPage select");

selectPage.addEventListener("change", function () {
    var sortValue = document.querySelector("#sortRating").value
    location.href = document.querySelector("#href").value+"&currentPage="+selectPage.value;
});

var selectSort = document.querySelector(".review select.sort");
selectSort.addEventListener("change", function () {
    var href = document.querySelector("#href").value;
    if(href.includes("sort")) {
        var index = href.search("sort")
        var type = href.substring(index + 5, index + 5 + 4);
        href = href.replace(type, selectSort.value);
    }
    location.href = href
});
