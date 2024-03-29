var category = document.getElementById("categoryAll");
var listCategory = document.getElementById("list-cate");
// ẩn hiện danh mục


category.addEventListener("click", function (){
    var categoryAllLi = document.querySelectorAll("#list-cate li");
    var height = categoryAllLi.length * 45;
    document.documentElement.style.setProperty("--heightShowCate", height+'px');
    if(listCategory.classList.contains("hideCategory")){
        category.style.overflow = "visible";
        listCategory.classList.replace("hideCategory", "showCategory");

    }else {
        listCategory.classList.replace("showCategory", "hideCategory");

        setTimeout(function () {
            if (listCategory.classList.contains("hideCategory")) {
                category.style.overflow = "hidden";
            }

        }, 250);
    }
});

