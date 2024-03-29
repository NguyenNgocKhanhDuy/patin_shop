var likes = document.querySelectorAll("like");
var carts = document.querySelectorAll("cart");
for (let i = 0; i < likes.length; i++) {
    likes[i].addEventListener("click", function (){
        event.stopPropagation();
    })
    carts[i].addEventListener("click", function (){
        event.stopPropagation();
    })
}
