var AuthModule;
$(function () {
    AuthModule = (function() {
        var self;

        function Auth() {
            self = this;

            self.SignUp = SignupService.init();
            self.Login = LoginService.init();
            Account.init();

        }


        return new Auth();
    })();
});