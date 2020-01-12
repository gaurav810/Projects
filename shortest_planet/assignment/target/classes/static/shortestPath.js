var app = angular.module("PlanetShortestPath", []);

app.controller("ShortestPathController", function($scope, $http) {
    
    $scope.shortestPaths = [];
    
    refreshDistanceData();
    
    function refreshDistanceData() {
        $http({
            method: 'GET',
            url: '/shortest-path-from-source'
        }).then(
            function(res) { // success
                $scope.shortestPaths = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
});
	
