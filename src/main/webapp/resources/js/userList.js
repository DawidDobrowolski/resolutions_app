$(document).ready(function () {


    $('#deleteModal').on('show.bs.modal', function (event) {
        var userId = $(event.relatedTarget).data('user-id');
        var userName = $(event.relatedTarget).data('user-name');
        $(this).find('.modal-body p #userName').text(userName);
        $('#deleteId').on('click', function () {
            window.location.href = "/user/delete/" + userId;
        })
    });

})