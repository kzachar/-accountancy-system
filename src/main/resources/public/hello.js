angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/invoices').
        then(
        function(response) {
            $scope.invoices = response.data;
        });

         function myController($scope){
          $scope.submitMyForm=function(){
          /* while compiling form , angular created this object*/
          var data=$scope.fields;
          /* post to server*/
          $http.post('http://localhost:8080/invoices', data);
          }


});