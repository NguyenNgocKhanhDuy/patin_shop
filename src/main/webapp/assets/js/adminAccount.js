$(document).ready(function (){
   var table = $('#dataUser').DataTable({
        ajax:{
            url:"getUser",
            type:"get",
            dataType:"json",
            dataSrc:""
        },
        columns:[
            {
              data: "avatar",
              className: "text-center align-middle",
              render: function (data) {
                  return `<img class="table-avatar" src="${data != null ? data : "./assets/images/logo.PNG"}">`
              }
            },
            {
                data: "fullName",
                className: 'text-center align-middle'
            },
            {
                data: "email",
                className :'text-center align-middle'
            },
            {
                data: null,
                className :'text-center align-middle' ,
                render: function (data){
                    return data.phone == undefined ? "" : data.phone
                }
            },
            {
                data: "role",
                className :'text-center align-middle',
                render: function (data) {
                    return (data.role == 0 ? "Người dùng" : (data.role == 1 ? "Mod" : "Admin"))
                }
            },
            {
                data: null,
                className :'text-center edit align-middle',
                render: function (data) {
                    return `<input type="hidden" value="${data.id}"/>
                            <i class="fa-solid fa-clipboard detail"></i>`
                }
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

    $('#dataUser tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var id = rowData.id;

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
