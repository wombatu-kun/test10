'use strict';

angular.module('searcher').factory('SearcherService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = '/search';

    var factory = {
        search: search
    };

    return factory;

    function search(req) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, req)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while searching');
                deferred.resolve(errResponse.data);
            }
        );
        return deferred.promise;
    }

}]);
