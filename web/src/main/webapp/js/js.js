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