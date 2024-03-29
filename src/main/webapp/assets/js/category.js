
function getCategory() {

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "showCategory", true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            var c = {}
            c = xhttp.response
            addInCateHtml(c);
        }
    }
}

getCategory();

function addInCateHtml(c) {
    var html = '';
    for (let i = 0; i < c.length; i++) {
        html += `<li>
                    <a href="listProductCategory?categoryID=${c[i].id}">
                        ${c[i].name}
                    </a>
                 </li>`
    }
    var list = document.querySelectorAll(".list-category");
    for (let i = 0; i < list.length; i++) {
        list[i].innerHTML = html;
    }
}

