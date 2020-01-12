var app = angular.module("PlanetShortestPath", []);

// Controller Part
app.controller("DistancPlanetsController", function($scope, $http) {
 
    $scope.createDistancPlanets = function() {
        clearFormData();
    }
    
    $scope.distancPlanets = [];
    
    $scope.distancPlanetsForm = {
        	id: 0,
        	sourcePlanet: "",
        	destinationPlanet: "",
        	distance: 1
        };
    
    refreshDistanceData();
    
    $scope.submitDistancePlanets = function() {
    	 
        var method = "";
        var url = "";
 
        if ($scope.distancPlanetsForm.id == 0) {
            method = "POST";
            url = '/planet-distance';
        } else {
            method = "PUT";
            url = '/planet-distance';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.distancPlanetsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
    
    function refreshDistanceData() {
        $http({
            method: 'GET',
            url: '/list-of-planet-distance'
        }).then(
            function(res) { // success
                $scope.distancPlanets = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
    $scope.editDistancPlanets = function(distance) {
        $scope.distancPlanetsForm.id = distance.id;
        $scope.distancPlanetsForm.sourcePlanet = distance.sourcePlanet.name;
        $scope.distancPlanetsForm.destinationPlanet = distance.destinationPlanet.name;
        $scope.distancPlanetsForm.distance = distance.distance;
    };
    
    function clearFormData() {
    	$scope.distancPlanetsForm.id = 0;
        $scope.distancPlanetsForm.sourcePlanet = "";
        $scope.distancPlanetsForm.destinationPlanet = "";
        $scope.distancPlanetsForm.distance = 1;
    };
    
    function _success(res) {
    	refreshDistanceData();
        clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
    
    $scope.deleteDistanse = function(distance) {
        $http({
            method: 'DELETE',
            url: '/planet-distance/' + distance.id
        }).then(_success, _error);
    };
});
	
