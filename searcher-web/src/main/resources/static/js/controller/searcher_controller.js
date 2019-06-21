'use strict';

angular.module('searcher').controller('SearcherController', ['$scope', 'SearcherService', function($scope, SearcherService) {
    var self = this;
    self.req = { value:null, page:1, pageSize:null };
    self.resp = { items:[], hasMore:false, page:1, pageSize:30, errorMessage:null };

    self.submit = submit;
    self.nextPage = nextPage;
    self.prevPage = prevPage;

    function search(req){
        SearcherService.search(req)
            .then(
                function(resp) {
                    self.resp = resp;
                }
        );
    }

    function submit() {
        self.req.page = 1;
        search(self.req);
    }

    function nextPage() {
        if (self.resp.hasMore === true) {
            self.req.value = self.resp.searchString;
            self.req.page++;
            search(self.req);
        }
    }

    function prevPage(next) {
        if (self.resp.page > 1) {
            self.req.value = self.resp.searchString;
            self.req.page--;
            search(self.req);
        }
    }

}]);
