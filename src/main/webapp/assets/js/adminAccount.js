$(document).ready(function (){
    var table = $('#data').DataTable({
        order:[1,"asc"],
        ajax:{
            url:"getUser",
            type:"get",
            dataType:"json",
            dataSrc:""
        },
        columns:[
            {
              data: "avatar",
              render: function (data) {
                  return `<img src="${data}">`
              }
            },
            {
                data: "fullName",
                className: 'text-center'
            },
            {
                data: "email",
                className :'text-center'
            },
            {
                data: "phone",
                className :'text-center' ,
            },
            {
                data: "role",
                className :'text-center'
            }
        ]
    })
    $('#data tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var idBill = rowData.id;

        $.ajax({
            url: 'deleteUserAdmin',
            type: 'POST',
            data: { id: id },
            success: function(response) {
                table.row(rowIndex).remove().draw();
                alert(response)
            },
            error: function(xhr, status, error) {
                alert(error)
            }
        });
    })
});
