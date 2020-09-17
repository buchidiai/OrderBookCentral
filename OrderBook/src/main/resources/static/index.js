
$(document).ready(function () {


    $('#dtBasicExample').dataTable({

        columnDefs: [{
                orderable: false,
                className: 'select-checkbox',
                targets: 0
            }],
        select: {
            style: 'os',
            selector: 'td:first-child'
        }
    });


    $('.table .openModal').on('click', function (e) {
        e.preventDefault();
        console.log('clicked modal');
        var href = $(this).attr('href');
        console.log(href, 'href');

        $.get(href, function (trade, status, c) {
            console.log(status);

            console.log(c);

        })

        $('#modalQuickView').modal();
    })


    function openModal(id) {
        
           console.log(id , 'id');
            var href = $(this).attr('href');
             console.log(href, 'href');
        $.ajax({
            url: "/car/" + id,
            success: function (response) {
                $("#carViewModal").empty().append(response);
                $("#carViewModal").modal("show");
            }
        });
    }
});




