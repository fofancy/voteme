/**
 * Created by User on 13.07.2017.
 */

var SignupService;
$(function () {
    SignupService = (function() {
        var self;

        var $signup_button = $('#signup-button');


        return {
            init : function () {
                self = this;

                $signup_button.click(function () {
                    var formData = {
                        username : $('#signup-username').val(),
                        email : $('#signup-email').val(),
                        password : $('#signup-password').val(),
                        passwordConfirm : $('#signup-confirm-password').val()
                    };

                    $.ajax({
                        type: 'POST',
                        url: 'auth/signup',
                        contentType: 'application/json',
                        data: JSON.stringify(formData),
                        // accepts: "application/json; charset=utf-8",
                        success: function(result) {
                            console.log(result);
                            window.location = '/account/login';
                            // localStorage.setItem("isAuthorized", true);
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