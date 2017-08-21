var AccountDomBinder;
$(function () {
    AccountDomBinder = (function() {
        var self;


        return {
            init : function () {
                self = this;

                self.$accountHeaderButton = $('#account-button');

                self.refresh();
            },

            refresh : function () {
                if(localStorage.getItem('isAuthorized') == 'true') {

                    self.$accountHeaderButton
                        .text('Account')
                        .attr('href', '/account/personal-page');
                }
                else {
                    self.$accountHeaderButton
                        .text('Login')
                        .attr('href', '/account/login');
                }

            }
        };
    })();
});