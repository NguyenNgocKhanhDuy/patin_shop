
var listImg = document.querySelectorAll(".product_detail .img img");
var listImgId = document.querySelectorAll(".product_detail .img .imgID");
var imgModal = document.getElementById("imageInModal");
var modalImg = document.querySelector(".modal-img");
var closeModalImg = document.getElementById("closeModalImg");
var modalImgContainer = document.querySelector(".modal-img-container");
var nextImg = document.getElementById("nextImg");
var previousImg = document.getElementById("previousImg");
var idImg = document.querySelector("#idImg");

closeModalImg.addEventListener("click", function () {
    modalImg.style.display = "none";
});

modalImg.addEventListener("click", function () {
    modalImg.style.display = "none";
});

modalImgContainer.addEventListener("click", function () {
    event.stopPropagation();
});

function clickImg() {
    for (let i = 0; i < listImg.length; i++) {
        listImg[i].addEventListener('click', function () {
            modalImg.style.display = "flex"
            imgModal.src = listImg[i].src
            idImg.value = listImgId[i].value
        })
    }
}
clickImg();

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
        idImg.value = listImgId[index].value
    }

    if (event.target.id == "previousImg") {
        if(index == 0) {
            index = listImg.length-1;
        }else {
            index--;
        }
        imgModal.src = listImg[index].src;
        idImg.value = listImgId[index].value
    }
}

nextImg.addEventListener("click", chageImg);
previousImg.addEventListener("click", chageImg);

var detail = document.querySelector(".product_detail .general .detail");

detail.addEventListener("click", function () {
    modalGeneral.style.display = "flex"
})

var modalGeneral = document.querySelector(".modal-general");
var modalGeneralContainer = document.querySelector(".modal-general .modal-container");
var modalGeneralDel = document.querySelector(".modal-general .del");

modalGeneral.addEventListener("click", function () {
    modalGeneral.style.display = "none"
})
modalGeneralDel.addEventListener("click", function () {
    modalGeneral.style.display = "none"
})
modalGeneralContainer.addEventListener("click", function () {
    event.stopPropagation();
})

var addDetail = document.querySelector(".product_detail .addProduct");
var modalAddDetail = document.querySelector(".modalAddDetail");
var modalAddDetailCon = document.querySelector(".modalAddDetail .modal-container");
var modalAddDetailDel = document.querySelector(".modalAddDetail .modal-container .del");

addDetail.addEventListener("click", function () {
    modalAddDetail.style.display = "flex"
})
modalAddDetail.addEventListener("click", function () {
    modalAddDetail.style.display = "none"
})
modalAddDetailDel.addEventListener("click", function () {
    modalAddDetail.style.display = "none"
})
modalAddDetailCon.addEventListener("click", function () {
    event.stopPropagation();
})


function modalDetail(id, size, color, select) {

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showModalAdmin?id=${id}&select=${select}&size=${size}&color=${color}`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInModal(c)
        }
    };
}



var productDetails = document.querySelectorAll(".product_detail .product-item .detail");

function showDetailProduct() {
    for (let i = 0; i < productDetails.length; i++) {
        productDetails[i].addEventListener("click", function () {
            modalDetail(document.querySelectorAll(".product_detail .id")[i].value,
                document.querySelectorAll(".product_detail .size")[i].textContent,
                document.querySelectorAll(".product_detail .color")[i].textContent, "product");
            modalEditDetail.style.display = "flex"
        });
    }
}

showDetailProduct();

var modalEditDetail = document.querySelector(".modalEditDetail")
var modalEditDetailContainer = document.querySelector(".modalEditDetail .modal-container")
var modalEditDetailDel = document.querySelector(".modalEditDetail .modal-container .del")

modalEditDetail.addEventListener("click", function () {
    modalEditDetail.style.display = "none"
})
modalEditDetailDel.addEventListener("click", function () {
    modalEditDetail.style.display = "none"
})
modalEditDetailContainer.addEventListener("click", function () {
    event.stopPropagation()
})

function addInModal(product) {
    var html =
                    `<div class="wrapper">
                        <input type="hidden" name="id" value="${product.productDetail.product.id}">
                        <input type="hidden" name="oldSize" value="${product.productDetail.size.id}">
                        <input type="hidden" name="oldColor" value="${product.productDetail.color.id}">
                        <div class="main">
                            <div class="hold-2">
                                <div class="hold">
                                    <label>Giá gốc</label>
                                    <input type="text" name="price" value="${product.productDetail.price / (1 - product.productDetail.product.salePercent)}">
                                </div>
                                <div class="hold">
                                    <label>Số lượng</label>
                                    <input type="number" name="quantity" value="${product.productDetail.quantity}">
                                </div>
                            </div>
                        </div>
                        <div class="more">
                            <div class="hold-2">
                                <div class="hold">
                                    <label>Màu sắc</label>
                                    <select class="color" name="color">
                                      
                                    </select>
                                </div>
                                <div class="hold">
                                    <label>Size</label>
                                    <select class="size" name="size">
                                        
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="add">Cập nhật</button>`
    document.querySelector(".modalEditDetail form").innerHTML = html;
    getColorDBForUpdate(product.productDetail.color.id)
    getSizeDBForUpdate(product.productDetail.size.id)
}

function getColorDBForUpdate(color) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showColor`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInColorForUpdate(c, color)
        }
    };
}

function getColorDB() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showColor`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInColor(c)
        }
    };
}
getColorDB()
function getSizeDBForUpdate(size) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showSize`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInSizeForUpdate(c, size)
        }
    };
}

function getSizeDB() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showSize`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInSize(c)
        }
    };
}

getSizeDB()
function addInColorForUpdate(c, color) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        if (c[i].id == color){
            html += `<option selected value="${c[i].id}">${c[i].name}</option>`
        }else {
            html += `<option value="${c[i].id}">${c[i].name}</option>`
        }
    }
    var colors = document.querySelectorAll("select.color");
    for (let i = 0; i < colors.length; i++) {
        colors[i].innerHTML = html;
    }
}

function addInColor(c) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        html += `<option value="${c[i].id}">${c[i].name}</option>`
    }
    var colors = document.querySelectorAll("select.color");
    for (let i = 0; i < colors.length; i++) {
        colors[i].innerHTML = html;
    }
}
function addInSizeForUpdate(c, size) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        if (c[i].id == size){
            html += `<option selected value="${c[i].id}">${c[i].name}</option>`
        }else {
            html += `<option value="${c[i].id}">${c[i].name}</option>`
        }
    }
    var sizes = document.querySelectorAll("select.size");
    for (let i = 0; i < sizes.length; i++) {
        sizes[i].innerHTML = html;
    }
}

function addInSize(c) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        html += `<option value="${c[i].id}">${c[i].name}</option>`
    }
    var sizes = document.querySelectorAll("select.size");
    for (let i = 0; i < sizes.length; i++) {
        sizes[i].innerHTML = html;
    }
}

var modalAddImg = document.querySelector(".modal-addImg")
var modalAddImgContainer = document.querySelector(".modal-addImg .modal-container")
var modalAddImgDel = document.querySelector(".modal-addImg .modal-container .del")

document.querySelector(".addImg").addEventListener("click", function () {
    modalAddImg.style.display = "flex";
})

modalAddImg.addEventListener("click", function () {
    modalAddImg.style.display = "none"
})
modalAddImgDel.addEventListener("click", function () {
    modalAddImg.style.display = "none"
})
modalAddImgContainer.addEventListener("click", function () {
    event.stopPropagation()
})