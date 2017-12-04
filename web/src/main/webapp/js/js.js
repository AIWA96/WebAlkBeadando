jQuery(function () {
    readJSON = function () {
        $.ajax({
            type: "GET",
            url: "accessories/getaccessories",
            dataType: "JSON",
            context: this,
            success: function (data) {
                console.log(data);
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        });

    }
})


$(document).ready(function () {
    $("a").click(function () {
        var ButtonText = $(this).text();
        $.get(ButtonText + ".html", function (data) {
            document.getElementById('content').innerHTML = data;
        });
    });
});