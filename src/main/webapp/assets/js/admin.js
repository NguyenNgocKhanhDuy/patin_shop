var subMenu = document.querySelector(".account ul li:first-child .sub_menu");

var manageList = document.querySelectorAll(".account .menu li a");

var isShow = false;

function showManage() {
    manageList[0].addEventListener("click", function () {
        if (isShow == false){
            subMenu.classList.replace("hideSubMenu", "showSubMenu");
            isShow = true;
        }else {
            subMenu.classList.replace("hideSubMenu", "showSubMenu");
            isShow = false;
        }
    });
}
showManage();


function modalDetail(id, select) {

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "showModalAdmin?id="+id+"&select="+select, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           var c = {}
            c = xhttp.response
            console.log(c)
            addInModal(c, select)
        }
    };
}

function addInModal(c, select) {
    if (select == "user"){
        addInModalUser(c);
    }else if (select == "product"){
        addInModalProduct(c);
    }else if(select == "brand"){
        addInModalBrand(c);
    }else if (select == "color"){
        addInModalColor(c);
    }
    else if (select == "size"){
        addInModalSize(c);
    }
}





function initDoB(day, month, year) {
    var daySelect = document.querySelectorAll("select.day");
    var monthSelect = document.querySelectorAll("select.month");
    var yearSelect = document.querySelectorAll("select.year");
    var htmlDay = "";
    for (let i = 1; i <= 31; i++) {
        if (i == day) {
            htmlDay += `<option selected>${i}</option>`;
        }else {
            htmlDay += `<option>${i}</option>`;
        }
    }
    for (let i = 0; i < daySelect.length; i++) {
        daySelect[i].innerHTML = htmlDay;
    }

    var htmlMonth = "";
    for (let i = 1; i <= 12; i++) {
        if (i == month) {
            htmlMonth += `<option selected>${i}</option>`;
        } else {
            htmlMonth += `<option>${i}</option>`;
        }
    }
    for (let i = 0; i < monthSelect.length; i++) {
        monthSelect[i].innerHTML = htmlMonth;
    }

    var htmlYear = "";
    for (let i = 1920; i <= new Date().getFullYear(); i++) {
        if (i == year) {
            htmlYear += `<option selected>${i}</option>`;
        }else {
            htmlYear += `<option>${i}</option>`;
        }
    }
    for (let i = 0; i < yearSelect.length; i++) {
        yearSelect[i].innerHTML = htmlYear;
    }
}
initDoB(new Date().getDate(), new Date().getMonth()+1, new Date().getFullYear())


var userDetails = document.querySelectorAll(".user-item .detail");


function showDetailUser() {
    for (let i = 0; i < userDetails.length; i++) {
        userDetails[i].addEventListener("click", function () {
            modalDetail(document.querySelectorAll(".user-item .id")[i].value, "user")
            modalEdit.style.display = "flex";

        });
    }
}

showDetailUser();



var brandDetails = document.querySelectorAll(".brand-item .detail");

function showDetailBrand() {
    for (let i = 0; i < brandDetails.length; i++) {
        brandDetails[i].addEventListener("click", function () {
            modalDetail(document.querySelectorAll(".brand-item .id")[i].value, "brand");

            modalEdit.style.display = "flex";

        });
    }
}

showDetailBrand();


var colorDetails = document.querySelectorAll(".color-item .detail");

function showDetailColor() {
    for (let i = 0; i < colorDetails.length; i++) {
        colorDetails[i].addEventListener("click", function () {
            modalDetail(document.querySelectorAll(".color-item .id")[i].value, "color");

            modalEdit.style.display = "flex";

        });
    }
}

showDetailColor();


var sizeDetails = document.querySelectorAll(".size-item .detail");

function showDetailSize() {
    for (let i = 0; i < sizeDetails.length; i++) {
        sizeDetails[i].addEventListener("click", function () {
            modalDetail(document.querySelectorAll(".size-item .id")[i].value, "size");

            modalEdit.style.display = "flex";

        });
    }
}

showDetailSize();


function addInModalUser(user) {
    var html = '';
    var date = new Date(user.dob)
    var per = document.querySelector("#perUser").value;

    html += `<div class="wrapper">
        <div class="main">
        <input type="hidden" name="id" value="${user.id}">
            <div class="hold">
                <label>Tên</label>
                <input type="text" name="fullname" value="${user.fullName}">
            </div>

            <div class="hold">
                <label>Email</label>
                <input type="email" name="email" value="${user.email}" readonly="readonly">
            </div>
           
            <div class="hold">
                <label>Địa chỉ</label>
                <input type="text" name="address" value="${user.address}">
            </div>

        </div>
        <div class="more">
            <div class="hold">
                <label>Số điện thoại</label>
                <input type="tel" name="phone" value="${user.phone}">
            </div>
            <div class="box">
            <div class="gender">
                <label>Giới tính:</label>
                <div class="selectGender">`

    if(user.sex == "Nam"){
        html += `
                    <input type="radio" name="gender" checked = "true" value="Nam">
                    <label>Nam</label>
                    <input type="radio" name="gender" value="Nữ">
                    <label>Nữ</label>`
    }else {
        html += `
                    <input type="radio" name="gender" value="Nam">
                    <label>Nam</label>
                    <input type="radio" name="gender" checked = "true" value="Nữ">
                    <label>Nữ</label>`
    }
    html +=
        `</div>
        </div>
        <div class="role">
                                <label>Vai trò:</label>
                                <select name="role">`
        if(user.role == 0){
            html += ` <option value="0" selected>Khách hàng</option>
                      <option value="1">Mod</option>
                      <option value="2">Admin</option>`
        }else if(user.role == 1){
            html += ` <option value="0">Khách hàng</option>
                      <option value="1" selected>Mod</option>
                      <option value="2">Admin</option>`
        }else if(user.role == 2){
            html += ` <option value="0">Khách hàng</option>
                      <option value="1">Mod</option>
                      <option value="2" selected>Admin</option>`
        }
        html += `
                                </select>
                            </div>
                            <div class="verify">
                                <label>Xác thực Email:</label>
                                <select name="verify">`
        if(user.verify == 0){
            html +=  ` <option value="0" selected>Chưa xác nhận</option>
                       <option value="1">Đã xác nhận</option>`
        }else if (user.verify == 1){
            html +=  ` <option value="0">Chưa xác nhận</option>
                       <option value="1" selected>Đã xác nhận</option>`
        }
        html += `
                  </select>
        </div>
            </div>
            <div class="dob">
                <label>Ngày sinh:</label>
                <div class="dob-input">
                    <select class="day" name="day">
                    </select>
                    <select class="month" name="month">
                    </select>
                    <select class="year" name="year">
                    </select>
                </div>
            </div>`
            if(per > 1){
                html += `<div>
                <input type="file" name="file">
            </div>`
            }

        html += `</div>
    </div>`
            if(per > 1){
                html += `<button type="submit" class="add">Cập nhật</button>`
            }else if (per < 2){
                html += `<button type="submit" class="add disabled">Cập nhật</button>`
            }

    document.querySelector(".modal-containerEditUser form").innerHTML = html;
    initDoB(date.getDate(), date.getMonth() + 1, date.getFullYear());
}

function addInModalProduct(product) {
    var html = "";

    html += `
    <div class="wrapper">
    <input type="hidden" name="id" value="">
        <div class="main">
            <div class="hold">
                <label>Tên sản phẩm</label>
                <input type="text">
            </div>
            <div class="hold-2">
                <div class="hold">
                    <label>Giá gốc</label>
                    <input type="text">
                </div>
                <div class="hold">
                    <label>Giảm giá</label>
                    <input type="text">
                </div>
            </div>
            <div class="hold-2">
                <div class="hold">
                    <label>Màu sắc</label>
                    <select name="color">
                        <option>Trắng</option>
                    </select>
                </div>
                <div class="hold">
                    <label>Size</label>
                    <select name="size">
                        <option>27</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="more">
            <div class="hold">
                <label>Thông tin khác</label>
                <textarea></textarea>
            </div>
            <div class="chooseImg">
                <input type="file">
                <input type="file">
                <input type="file">
                <input type="file">
            </div>
        </div>
    </div>
    <button type="submit" class="add">Thêm</button>`
    document.querySelector(".modal-containerEditProduct form").innerHTML = html
}

function addInModalBrand(brand) {
    var html = "";
    html += `
    <input type="hidden" name="id" value="${brand.id}">
    <input type="text" placeholder="Nhập tên danh mục" name="name" value="${brand.name}">
    <button type="submit" class="add">Cập nhật</button>`
    document.querySelector(".modal-containerEditBrand form").innerHTML = html;
}

function addInModalColor(color) {
    var html = `
    <input type="hidden" name="id" value="${color.id}">
    <input type="text" placeholder="Nhập tên màu sắc" name="name" value="${color.name}">
    <button type="submit" class="add">Cập nhật</button>`
    document.querySelector(".modal-containerEditColor form").innerHTML = html;
}

function addInModalSize(size) {
    var html = ` 
    <input type="hidden" name="id" value="${size.id}">
    <input type="text" placeholder="Nhập số kích thước" name="name" value="${size.name}">
    <button type="submit" class="add">Cập nhật</button>`
    document.querySelector(".modal-containerEditSize form").innerHTML = html
}

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

var modalEdit = document.querySelector(".modal-edit");
var modalEditContainer = document.querySelector(".modal-edit .modal-container");
var modalEditDel = document.querySelector(".modal-edit .modal-container .del");
var add = document.querySelector(".right .search .add");
add.addEventListener("click", function () {
    modal.style.display = "flex";
})
modalEdit.addEventListener("click", function () {
    modalEdit.style.display = "none";
});

modalEditDel.addEventListener("click", function () {
    modalEdit.style.display = "none";
});

modalEditContainer.addEventListener("click", function () {
    event.stopPropagation();
});


function getColorDB() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showColor`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInColor(c)
        }
    };
}
function getSizeDB() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", `showSize`, true)
    xhttp.responseType = 'json'
    xhttp.send()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var c = {}
            c = xhttp.response
            console.log(c)
            addInSize(c)
        }
    };
}
getColorDB()
getSizeDB()
function addInColor(c) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        html += `<option value="${c[i].id}">${c[i].name}</option>`
    }
    var colors = document.querySelectorAll("select.color");
    for (let i = 0; i < colors.length; i++) {
        colors[i].innerHTML = html;
    }
}
function addInSize(c) {
    var html = "";
    for (let i = 0; i < c.length; i++) {
        html += `<option value="${c[i].id}">${c[i].name}</option>`
    }
    var sizes = document.querySelectorAll("select.size");
    for (let i = 0; i < sizes.length; i++) {
        sizes[i].innerHTML = html;
    }
}
