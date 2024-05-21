$('document').ready(function () {
    var id = $('#billID').val()
    $('#data').DataTable({
        ajax: {
            url: `getBillDetail?id=${id}`,
            type: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {data: "product.name"},
            {data: "product.color.name"},
            {data: "product.size.name"},
            {data: "quantity"},
            {
                data: "product.price",
                render: function (data) {
                    return parseFloat(data).toLocaleString('vi-VN', {
                        style: 'currency',
                        currency: 'VND'
                    })
                }
            },
        ]
    })

    if ($('.status').val() == "Dang van chuyen"){
        $('.status option').eq(0).prop('disabled', true)
        $('.status option').eq(1).prop('disabled', true)
        $('.status option').eq(2).prop('disabled', true)
        $('.status option').eq(5).prop('disabled', true)
    }

    $('.status').change(function () {
        var status = $(this).val()
        if (status == "Dang van chuyen"){
            $('.status option').eq(0).prop('disabled', true)
            $('.status option').eq(1).prop('disabled', true)
            $('.status option').eq(2).prop('disabled', true)
            $('.status option').eq(5).prop('disabled', true)
        }else if (status == "Da giao") {
            $('.status').prop('disabled', true)
        }else if (status == "Da huy"){
            $('.status').prop('disabled', true)
        }
        $.ajax({
            url: 'changeStatus',
            type: 'POST',
            data: {
                id: $('#billID').val(),
                status: status
            },
            success: function (response) {
                alert(response)
            },
            error: function (xhr, status, error){
                alert(error)
            }
        })
    })
})