


function preventLink() {
    var input = document.querySelectorAll(".product-item .boxQuantity")
    for (let i = 0; i < input.length; i++) {
        input[i].addEventListener("click", function () {
            event.preventDefault();
        })
    }

}

preventLink();

function quantity() {
    var minusList = document.querySelectorAll(".product-item .quantity .minus");
    var plusList = document.querySelectorAll(".product-item .quantity .plus");
    var deleteList = document.querySelectorAll(".product-item .del");
    var productItemsID = document.querySelectorAll(".product-item input.id");
    var productItemsSize = document.querySelectorAll(".product-item input.size");
    var productItemsColor = document.querySelectorAll(".product-item input.color");

    for (let i = 0; i < minusList.length; i++) {
        minusList[i].addEventListener("click", function () {
            sendQuantity(productItemsID[i].value, productItemsSize[i].value, productItemsColor[i].value, "minus");
        })
    }

    for (let i = 0; i < plusList.length; i++) {
        plusList[i].addEventListener("click", function () {
            sendQuantity(productItemsID[i].value, productItemsSize[i].value, productItemsColor[i].value, "plus");
        })
    }

    for (let i = 0; i < deleteList.length; i++) {
        deleteList[i].addEventListener("click", function () {
            event.preventDefault();
            var url = `cartDelete?id=${productItemsID[i].value}&size=${productItemsSize[i].value}&color=${productItemsColor[i].value}`
            location.href = url;
            // sendQuantity(productItemsID[i].value, productItemsSize[i].value, productItemsColor[i].value, "delete");
        })
    }


}

quantity()

    function sendQuantity(id, size, color, type) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", `cartQuantity?id=${id}&size=${size}&color=${color}&type=${type}`, true);
        xhttp.responseType = 'json'
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (this.status == 200 && this.readyState == 4) {
                var cart = xhttp.response
                addCartHtml(cart)
            }
        }
    }

    function addCartHtml(cart) {
        var html = '';
        var totalPrice = document.querySelector(".pay_total .totalValue");

        var keys = [];
        for (const cartKey in cart) {
            keys.push(JSON.parse(cartKey))
        }
        var sum = 0;

        var htmlLink = `<a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>`

        if (keys.length > 0) {
            htmlLink += `<span class="amount">${keys.length}</span>`
        }
        document.querySelector(".user .cartLink").innerHTML = htmlLink

        for (let i = 0; i < Object.keys(cart).length; i++) {
            var product = cart[JSON.stringify(keys[i])].product;
            sum += (product.productDetail.price * cart[JSON.stringify(keys[i])].quantity)
            html +=  `
            <a href="productDetail?productID=${keys[i].id}" class="product-item">
                <input type="hidden" class="id" value="${keys[i].id}"/>
                <input type="hidden" class="size" value="${keys[i].size}"/>
                <input type="hidden" class="color" value="${keys[i].color}"/>
                <div class="img-name">
                    <img src="${product.img}" alt="">
                        <p class="name">${product.productDetail.product.name}</p>
                </div>
                <p class="size">${product.productDetail.size.name}</p>
                <p class="color">${product.productDetail.color.name}</p>
                <p class="price">
                   ${changeCurrency(product.productDetail.price)}
                </p>
                <div class="boxQuantity">`
                   if(cart[JSON.stringify(keys[i])].quantity >= product.quantity){
                    html +=`    
                        <div class="quantity disabled">
                            <i class="fa-solid fa-minus minus"></i>
                            <input type="number" value="${cart[JSON.stringify(keys[i])].quantity}">
                            <i class="fa-solid fa-plus plus disabledNoBg"></i>
                        </div>`
                    }else if(cart[JSON.stringify(keys[i])].quantity <= 1){
                       html +=`    
                        <div class="quantity disabled">
                            <i class="fa-solid fa-minus minus disabledNoBg"></i>
                            <input type="number" value="${cart[JSON.stringify(keys[i])].quantity}">
                            <i class="fa-solid fa-plus plus"></i>
                        </div>`
                   }
                   else{
                       html += `
                       <div class="quantity">
                            <i class="fa-solid fa-minus minus"></i>
                            <input type="number" value="${cart[JSON.stringify(keys[i])].quantity}">
                            <i class="fa-solid fa-plus plus"></i>
                        </div>`
                    }
              html += ` </div>
                <p class="total">
                    ${changeCurrency(product.productDetail.price * cart[JSON.stringify(keys[i])].quantity)}
                </p>
                <i class="fa-solid fa-xmark del"></i>
            </a>  `
        }
        totalPrice.textContent = changeCurrency(sum);
        document.querySelector(".product-list").innerHTML = html;
        quantity();
        preventLink()
    }

function changeCurrency(price) {
    return parseFloat(price).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });

}

