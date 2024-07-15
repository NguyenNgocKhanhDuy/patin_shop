$(document).ready(function () {
    $('#data').DataTable({
        ajax:{
            url: "getStatistic",
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
                data: "quantity",
                className: 'text-center align-middle',
            },

        ]
    })
})