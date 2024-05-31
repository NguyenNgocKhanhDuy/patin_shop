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
              className: "table-avatar",
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
                data: null,
                className :'text-center' ,
                render: function (data){
                    return data.address == null ? "" : data.address
                }
            },
            {
                data: "role",
                className :'text-center'
            },
            {
                data: null,
                className :'text-center edit',
                render: function (data) {
                    return `<input type="hidden" value="${data.id}"/>
                            <i class="fa-solid fa-clipboard detail"></i>`
                }
            },
            {
                data: null,
                className :'text-center delete',
                render: function (data) {
                    return `<i class="fa-solid fa-xmark del"></i>`
                }
            }
        ]
    })
    $('#data tbody').on('click', 'td.delete', function () {
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
