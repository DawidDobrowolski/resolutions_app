$(document).ready(function () {


    $('#deleteModal').on('show.bs.modal', function (event) {
        var resolutionId = $(event.relatedTarget).data('resolution-id');
        var resolutionName = $(event.relatedTarget).data('resolution-name');
        $(this).find('.modal-body p #resolutionName').text(resolutionName);
        $('#deleteId').on('click', function () {
            window.location.href = "/resolutionType/delete/" + resolutionId;
        })
    });

})