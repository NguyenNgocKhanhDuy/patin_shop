var forgetPass = document.querySelector("#forgetPass");
var modal = document.querySelector(".modal")
var modalContainer = document.querySelector(".modal-container")
var modalDel = document.querySelector(".modal .del");

function showModal() {
    modal.style.display = "flex";
}
function hideModal() {
    modal.style.display = "none";
}

forgetPass.addEventListener("click", showModal);
modalDel.addEventListener("click", hideModal);
modal.addEventListener("click", hideModal);
modalContainer.addEventListener("click", function () {
    event.stopPropagation();
})
