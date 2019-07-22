$(document).ready(function () {

    $('#deleteModal').on('show.bs.modal', function (event) {
        var activityId = $(event.relatedTarget).data('activity-id');
        var resolutionName = $(event.relatedTarget).data('resolution-name');
        $(this).find('.modal-body p #resolutionName').text(resolutionName);
        $('#deleteId').on('click', function () {
            window.location.href = "/activity/delete/" + activityId;
        })
    });

})