var modal = document.querySelector(".modalRateImg");
var modalContainer = document.querySelector(".modalRateImg .modal-img-container");
var modalDel = document.querySelector(".modalRateImg .del")
var modalImg = document.querySelector(".modalRateImg img");

var images = document.querySelectorAll(".review .image img");

modal.addEventListener("click", function () {
    modal.style.display = "none"
})

modalDel.addEventListener("click", function () {
    modal.style.display = "none"
})

modalContainer.addEventListener("click", function () {
    event.stopPropagation();
})

function clickShowModalImg() {
    for (let i = 0; i < images.length; i++) {
        images[i].addEventListener("click", function () {

            modal.style.display = "flex"
            modalImg.src = images[i].src
        })
    }
}

clickShowModalImg()