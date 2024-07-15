$(document).ready(function () {

    $('#data').DataTable({
        dom: 'Bfrtip',
        buttons: [ {
            extend: 'excelHtml5',
            customize: function( xlsx ) {
                var sheet = xlsx.xl.worksheets['sheet1.xml'];

                $('row c[r^="C"]', sheet).attr( 's', '2' );
            }
        } ],

        ajax:{
            url: "getLog",
            type:"get",
            dataType:"json",
            dataSrc:""
        },
        columns:[
            {
                data: null,
                className: "text-center align-middle",
                render: function (data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            {
                data: null, // Địa chỉ IP
                className: 'text-center align-middle',
                render: function(data) {
                    return data.ip != undefined ? data.ip : "";
                }
            },
            {
                data: null, // Mức độ log
                className: 'text-center align-middle',
                render: function(data) {
                    return data.level != undefined ? data.level : "";
                }
            },
            {
                data: null, // Địa chỉ
                className: 'text-center align-middle',
                render: function(data) {
                    return data.address != undefined ? data.address : "";
                }
            },
            {
                data: null, // Giá trị trước
                className: 'text-center align-middle',
                render: function(data) {
                    return data.preValue != undefined ? data.preValue : "";
                }
            },
            {
                data: null, // Giá trị sau
                className: 'text-center align-middle',
                render: function(data) {
                    return data.value != undefined ? data.value : "";
                }
            },
            {
                data: null, // Ngày tạo
                className: 'text-center align-middle',
                render: function (data) {
                    return data.createAt != undefined ? data.createAt : "";
                }
            },
            {
                data: null, // Ngày cập nhật
                className: 'text-center align-middle',
                render: function (data) {
                    return data.updateAt != undefined ? data.updateAt : "";
                }
            }
        ]
    })


})