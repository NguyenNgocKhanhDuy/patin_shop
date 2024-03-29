var modal = document.querySelector(".modal");
var modalDel = document.querySelector(".modal .modal-container .del");
var modalContainer = document.querySelector(".modal .modal-container");
modal.addEventListener("click", function () {
    modal.style.display = "none";
});

modalDel.addEventListener("click", function () {
    modal.style.display = "none";
});

modalContainer.addEventListener("click", function () {
    event.stopPropagation();
});

document.querySelector(".bill_detail .detail").addEventListener("click", function () {
    modal.style.display = "flex"
})