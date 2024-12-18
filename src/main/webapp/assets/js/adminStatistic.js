$(document).ready(
    function () {
    $('#data').DataTable({
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
    });
        $('#data2').DataTable({
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
                url: "getBestSale",
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
                    data: "product.productDetail.product.id",
                    className: 'text-center align-middle'
                },
                {
                    data: "product.productDetail.product.name",
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