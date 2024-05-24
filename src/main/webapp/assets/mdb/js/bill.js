$('document').ready(function () {
    $('#data').DataTable({
        ajax: {
            url: "getBill",
            type: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: null,
                render: function (data) {
                    return `<a href="showBillDetail?id=${data.id}">${data.name}</a>`
                }
            },
            {data: "date"},
            {data: "status"},
            {data: "note"},
            {data: "user.email"}
        ]
    })
})