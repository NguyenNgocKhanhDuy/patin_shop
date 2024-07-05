$(document).ready(function (){
    console.log('Show Bill')
    var table = $('#data').DataTable({
        ajax:{
            url:"getBill",
            type:"get",
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
                data:null,
                className: 'text-center align-middle',
                render: function (data){
                    return `<a href="showBillDetailAdmin?id=${data.id}">${data.name}</a>`
                }
            },
            {
                data: "date",
                className :'text-center align-middle'
            },
            {
                data: "status",
                className :'text-center align-middle'
            },
            {
                data: "payment",
                className :'text-center align-middle'
            },
            {
                data: null,
                className :'text-center delete align-middle',
                render: function (data) {
                    return `<i class="fa-solid fa-xmark del"></i>`
                }
            }
        ]
    })


    $('#data tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var idBill = rowData.id;

        $.ajax({
            url: 'deleteBill',
            type: 'POST',
            data: { id: idBill },
            success: function(response) {
                table.row(rowIndex).remove().draw();
                alert("Deleted bill ")
            },
            error: function(xhr, status, error) {
                alert("Delete bill fail ")
            }
        });
    })
});


function checkBillStatus(status) {
    var htmlSelect = `<select ${status == 5 ? "disabled" : ""}>`
    var statusList = ["Đang xác nhận", "Đang xử lý", "Đang đóng gói", "Đang giao", "Đã giao", "Huỷ đơn"]
    if (status == 3) {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${status}" ${i < 3 ? "disabled" : (i == status ? "selected" : "")}>${statusList[i]}</option>`
        }
    }else if (status == 4) {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${status}" ${i == 5 ? "disabled" : (i == status ? "selected" : "")}>${statusList[i]}</option>`
        }
    }else if (status == 5) {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${status}" ${i != status ? "disabled" : (i == status ? "selected" : "")}>${statusList[i]}</option>`
        }
    }else {
        for (i = 0; i < 6; i++) {
            htmlSelect += `<option value="${status}" ${i == status ? "selected" : ""}>${statusList[i]}</option>`
        }
    }
    htmlSelect += `</select>`
    return htmlSelect;
}