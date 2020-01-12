var app = angular.module("PlanetShortestPath", []);

// Controller Part
app.controller("PlanetShortestPathController", function($scope, $http) {
 
    $scope.planets = [];
    $scope.planetNameForm = {
    	id: 0,
    	name: ""
    };
 
    // Now load the data from server
    refreshPlanetData();
 
    // HTTP POST/PUT methods for add/edit employee  
    // Call: http://localhost:8080/employee
    $scope.submitPlanetName = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.planetNameForm.id == 0) {
            method = "POST";
            url = '/planet';
        } else {
            method = "PUT";
            url = '/planet';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.planetNameForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createPlanet = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/employee/{empId}
    $scope.deletePlanet = function(planet) {
        $http({
            method: 'DELETE',
            url: '/planet/' + planet.id
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editPlanet = function(planet) {
        $scope.planetNameForm.id = planet.id;
        $scope.planetNameForm.name = planet.name;
    };
 
    // Private Method  
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/employees
    function refreshPlanetData() {
        $http({
            method: 'GET',
            url: '/planet'
        }).then(
            function(res) { // success
                $scope.planets = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
    	refreshPlanetData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
    	 $scope.planetNameForm.id = 0;
         $scope.planetNameForm.name = "";
    };
    
    
    //----------------------------
    
    $scope.distancPlanets = [];
    
    refreshDistanceData();
    
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
    
    
});
	
