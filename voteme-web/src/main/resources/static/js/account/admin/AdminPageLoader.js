var AdminPageLoader;
$(function () {
    AdminPageLoader = (function() {
        var self;

        return {
            init : function() {
                var authorization = JSON.parse(localStorage.getItem("Authorization"));
                if(authorization !== null &&
                    authorization.isAuthorized === true) {

                    $.ajax({
                        type: 'GET',
                        url: '/account/admin/content',
                        headers : {'X-Token' : authorization.token},
                        success: function (result) {
                            $('#content').html(result);
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }
                    })
                }
                else {
                    alert('Non authorized error')
                }
            }
        };
    })();
});