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

// function onSignIn(googleUser) {
//     var profile = googleUser.getBasicProfile();
//     console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
//     console.log('Name: ' + profile.getName());
//     console.log('Image URL: ' + profile.getImageUrl());
//     console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
// }
