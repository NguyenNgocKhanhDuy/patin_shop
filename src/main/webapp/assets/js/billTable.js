$(document).ready(function (){
    var table = $('#billTable').DataTable({
        order:[1,"asc"],
        ajax:{
            url:"getBill",
            type:"get",
            dataType:"json",
            dataSrc:""
        },
        columns:[
            {data:null,
                render: function (data){
                    return `<a href="showOrderDetail?id=${data.id}">${data.name}`
                }},
            {data: "date",
                className :'text-center'
            },
            {data: null,
                className :'text-center align-middle',
                render: function (data) {
                    var statusString = ""
                    switch (data.status) {
                        case 0:
                            statusString = "Đang xác nhận";
                            break;
                        case 1:
                            statusString = "Đang xử lý";
                            break;
                        case 2:
                            statusString = "Đang đóng gói";
                            break;
                        case 3:
                            statusString = "Đang giao";
                            break;
                        case 4:
                            statusString = "Đã giao";
                            break;
                        case 5:
                            statusString = "Huỷ đơn";
                            break;
                        default:
                            statusString = "Đang xử lý";
                            break;
                    }
                    return statusString
                }

            },
            {data: "payment",
                className :'text-center' },
            {data: null,
                className: 'text-center delete',
            render:function (data){
                return '<i class="fa-solid fa-trash maction"></i>'
            }}
        ]
    })
    $('#billTable tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var idBill = rowData.id;

            $.ajax({
                url: 'deleteBill',
                type: 'POST',
                data: { id: idBill },
                success: function(response) {
                    if (response == "Đơn hàng huỷ thành công") {
                        table.row(rowIndex).remove().draw();
                    }
                    alert(response)
                },
                error: function(xhr, status, error) {
                    alert(error)
                }
            });
        })
});
