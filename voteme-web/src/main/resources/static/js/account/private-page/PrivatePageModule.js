var PrivatePageModule;
$(function () {
    PrivatePageModule = (function() {
        var self;

        function PrivatePageModule() {
            self = this;

            self.privatePageLoader = PrivatePageLoader.init();

        }


        return new PrivatePageModule();
    })();
});