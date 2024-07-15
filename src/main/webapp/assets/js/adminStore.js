$(document).ready(function () {
   var table =  $('#data').DataTable({
       dom: 'Bfrtip',
       buttons: [
           {
               extend: 'excelHtml5',
               text: 'Export to Excel'
           },
           {
               extend: 'print',
               text: 'Print'
           }
       ],
        ajax:{
            url: "getStore",
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
                data: "product.id",
                className: 'text-center align-middle'
            },
            {
                data: "size.id",
                className: 'text-center align-middle'

            },
            {
                data: "color.id",
                className: 'text-center align-middle'
            },
            {
                data: "name",
                className: 'text-center align-middle'
            },
            {
                data: "quantity",
                className: 'text-center align-middle',
            },
            {
                data: "date",
                className: 'text-center align-middle'
            },
            {
                data: "price",
                className: 'text-center align-middle'
            },
            {
                data: "salePercent",
                className: 'text-center align-middle'
            }
        ],

    })

})