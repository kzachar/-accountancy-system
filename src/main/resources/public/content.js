var app = angular.module('postserviceApp', []);

app.controller('postserviceCtrl', function($scope, $http) {

    $scope.id = null;

    $scope.identifier = null;

    $scope.issuedDate = null;

    $scope.buyer = null;

    $scope.name = name;

    $scope.lblMsg = null;

    $scope.postdata = function(id, identifier, issuedDate, buyer, name, taxId, streetAndNumber, postalCode, location, seller, name2, taxId2, streetAndNumber2, postalCode2, location2, entries) {

        var data = {

            id: id,

            identifier: identifier,

            issuedDate: issuedDate,

            buyer: {
                name: name,
                taxId: taxId,
                streetAndNumber: streetAndNumber,
                postalCode: postalCode,
                location: location
            },

            seller: {
                name: name2,
                taxId: taxId2,
                streetAndNumber: streetAndNumber2,
                postalCode: postalCode2,
                location: location2
            },

            entries: [],
        };

        //Call the services

        $http.post('http://localhost:8080/invoices', JSON.stringify(data)).then(function(response) {

            if (response.data)

                $scope.msg = "Post Data Submitted Successfully!";

        }, function(response) {

            $scope.msg = "Service not Exists";

            $scope.statusval = response.status;

            $scope.statustext = response.statusText;

            $scope.headers = response.headers();

        });

    };

});