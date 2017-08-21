/**
 * Created by shaylin3 on 03.07.2017.
 */

var Account = (function () {
    return {
        // loginContent: {
        //
        // },
        init: function () {
            var self = this;



            self.loginContent = {
                $formLogin: $('#form-login'),
                $formLost: $('#lost-form'),
                $formSignup: $('#signup-form'),
                $divForms: $('#login-forms'),
                $modalAnimateTime: 80,
                $msgAnimateTime: 150,
                $msgShowTime: 2000
            };

            $('#signin-signup-a').click( function () {
                cardAnimate(self.loginContent.$formLogin, self.loginContent.$formSignup);
            });
            $('#signin-forget-a').click( function () {
                cardAnimate(self.loginContent.$formLogin, self.loginContent.$formLost);
            });
            $('#forget-signin-a').click( function () {
                cardAnimate(self.loginContent.$formLost, self.loginContent.$formLogin);
            });
            $('#forget-signup-a').click( function () {
                cardAnimate(self.loginContent.$formLost, self.loginContent.$formSignup);
            });
            $('#signup-signin-a').click( function () {
                cardAnimate(self.loginContent.$formSignup, self.loginContent.$formLogin);
            });
            $('#signup-forget-a').click( function () {
                cardAnimate(self.loginContent.$formSignup, self.loginContent.$formLost);
            });
            
            function cardAnimate($previousForm, $nextForm) {
                var $oldH = $previousForm.height();
                var $newH = $nextForm.height();
                self.loginContent.$divForms.css("height",$oldH);
                $previousForm.fadeToggle(self.loginContent.$modalAnimateTime, function(){
                    self.loginContent.$divForms.animate({height: $newH}, self.loginContent.$modalAnimateTime, function(){
                        $nextForm.fadeToggle(self.loginContent.$modalAnimateTime);
                    });
                });
            }

        },
        signin : function () {

        }


    }
})();