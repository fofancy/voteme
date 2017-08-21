var LoginService;
$(function () {
    LoginService = (function() {
        var self;

        var $signin_button = $('#signin-btn');


        return {
            init : function () {
                self = this;

                $signin_button.click(function () {
                    var formData = {
                        username : $('#signin-email').val(),
                        // email : $('#signup-email').val(),
                        password : $('#signin-pass').val()
                        // passwordConfirm : $('#signup-confirm-password').val()
                    };

                    $.ajax({
                        type: 'POST',
                        url: '/account/auth/login',
                        contentType: 'application/json',
                        data: JSON.stringify(formData),
                        // accepts: "application/json; charset=utf-8",
                        success: function(result) {
                            console.log(result);
                            localStorage.setItem("Authorization", JSON.stringify({
                                isAuthorized : true,
                                token : JSON.parse(result).tokenValue
                            }));
                            window.location = '/account/personal-page';
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }
                    })
                });

                return self;
            }
        }
    })();
});