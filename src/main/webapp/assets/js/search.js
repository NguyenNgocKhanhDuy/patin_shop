var showBox = document.querySelector("#category-search .search ul")
var searchInput = document.querySelector("#category-search .search input");
var body = document.querySelector("body")
var btnSearch = document.getElementById("searchBtn");


function search() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "search?search="+searchInput.value, true);
    xhttp.responseType = 'json'
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (searchInput.value.length == 0) {
                showBox.innerHTML = "";
                showBox.style.boxShadow = "0 0 0 0 #fff";
            }else {
                var c= {}
                c = xhttp.response
                if (c.length > 0) {
                    addInSearchHtml(c)
                }else {
                    showBox.style.boxShadow = "0 0 0 0 #fff";
                }
            }
        }
    };
}

function addInSearchHtml(c) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        html += `<li>  
                     <a href="productDetail?productID=${c[i].productDetail.product.id}">  
                         <span class="item"> 
                             <img src="${c[i].img}" alt=""/>
                             <p class="name">${c[i].productDetail.product.name}</p>
                         </span> 
                     </a> 
                 </li>`
    }
    showBox.innerHTML = html;
    showBox.style.boxShadow = "0px 4px 6px 2px #ccc";
}

btnSearch.addEventListener("click", search);
searchInput.addEventListener("input", search);
searchInput.addEventListener("click", search);
body.addEventListener("click", function (){
    showBox.innerHTML = ""
    showBox.style.boxShadow = "0 0 0 0 #fff";
})

searchInput.addEventListener("click", function () {
    event.stopPropagation();
});

showBox.addEventListener("click", function () {
    event.stopPropagation();
});

