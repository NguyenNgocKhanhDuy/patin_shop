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
var selectStatus = document.querySelector(".modal-bill select.status")
document.querySelector(".bill_detail .detail").addEventListener("click", function () {
    modal.style.display = "flex"
    var status = document.querySelector(".modal-bill .billStatus").value
    selectStatus.innerHTML = checkBillStatus(status)
})

$(document).ready(function (){
    var id = document.querySelector(".bill_detail .billID").value
    $('#data').DataTable({
        ajax:{
            url:"getBillDetail",
            type:"get",
            data: function (d) {
                d.id = id;
                return d;
            },
            dataType:"json",
            dataSrc:""
        },
        columns:[
            {
                data: null,
                className: 'text-center align-middle',
                render: function (data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            {
                data: "product.productDetail.product.name",
                className: 'text-center align-middle',
            },
            {
                data: "color.name",
                className :'text-center align-middle'
            },
            {
                data: "size.name",
                className :'text-center align-middle'
            },
            {
                data: null,
                className :'text-center align-middle',
                render: function (data) {
                    return changeCurrency(data.price)
                }
            },
            {
                data: "quantity",
                className :'text-center align-middle'
            },
        ]
    })


});


function checkBillStatus(status) {
    var htmlSelect = ``
    var statusList = ["Đang xác nhận", "Đang xử lý", "Đang đóng gói", "Đang giao", "Đã giao", "Huỷ đơn"]
    if (status == 3) {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${i}" ${i < 3 ? "disabled" : (i == status ? "selected" : "")}>${statusList[i]}</option>`
        }
    }else if (status == 4 || status == 5) {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${i}" ${i != status ? "disabled" : (i == status ? "selected" : "")}>${statusList[i]}</option>`
        }
    }else {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${i}" ${i == status ? "selected" : ""}>${statusList[i]}</option>`
        }
    }
    return htmlSelect;
}

function changeCurrency(text) {
    return parseFloat(text).toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
}