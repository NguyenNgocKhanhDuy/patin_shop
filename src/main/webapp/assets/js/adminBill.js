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
                render: function (data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            {
                data:null,
                render: function (data){
                    return `<a href="showBillDetailAdmin?id=${data.id}">${data.name}</a>`
                }
            },
            {
                data: "date",
                className :'text-center'
            },
            {
                data: "status",
                className :'text-center' ,
            },
            {
                data: "payment",
                className :'text-center'
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
