var carousel = document.getElementById("carousel");

slideProduct();

function slideProduct() {
    // tự chuyển động sale
    var items = document.getElementsByClassName("product-item");
    var next = document.getElementById("next");
    var previous = document.getElementById("previous");

// kích thước 1 item
    var itemWidth = 300;

    var step = itemWidth;

    var countMax = (items.length*itemWidth)/step;

    var count = 0;
    var direct = "next";

// Nhấn next
    next.addEventListener("click", function (){
        carousel.scrollLeft += step;
        count++;
        if (count == (countMax-2)){
            count = 1;
            direct = "previous";
        }
    });

// Nhấn previous
    previous.addEventListener("click", function () {
        carousel.scrollLeft -= step;
        count++;
        if (count == (countMax-2)){
            count = 1;
            direct = "next";
        }
    });


// Tự lướt
    setInterval(function (){
        count++;
        if (count == (countMax-2)){
            count = 1;
            if (direct == "next"){
                direct = "previous";
            }else if(direct == "previous"){
                direct = "next";
            }
        }

        if (direct == "next"){
            carousel.scrollLeft += step;
        }else {
            carousel.scrollLeft -= step;
        }
    }, 3000);
}
