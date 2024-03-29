

function showHide() {
    var mainMenuLi = document.querySelectorAll(".account ul.menu > li");
    for (let i = 0; i < mainMenuLi.length; i++) {
        mainMenuLi[i].addEventListener("click", function () {
            for (let j = 0; j < mainMenuLi.length; j++) {
                mainMenuLi[j].classList.remove("activeAccountNav");
            }
            mainMenuLi[i].classList.add("activeAccountNav");
        })
    }
}
showHide();

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
var dobU =document.querySelector("#DOB").value
if (dobU == 0){
    initDoB(new Date().getDate(), new Date().getMonth()+1, new Date().getFullYear())
}else{
    var year = dobU.substring(0, 4)
    var month = dobU.substring(5, 7);
    var day = dobU.substring(8, 10);
    initDoB(day, month, year)
}


