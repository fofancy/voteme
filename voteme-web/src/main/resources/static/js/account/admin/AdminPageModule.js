var AdminPageModule;
$(function () {
    AdminPageModule = (function() {
        var self;

        function AdminPageModule() {
            self = this;

            self.adminPageLoader = AdminPageLoader.init();

        }


        return new AdminPageModule();
    })();
});