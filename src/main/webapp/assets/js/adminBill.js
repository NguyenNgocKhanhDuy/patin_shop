$(document).ready(function (){
    var table = $('#data').DataTable({
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
            {data: "status",
                className :'text-center' ,
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
