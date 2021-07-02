/**
 * 
 */
var app = angular.module("bookApp", []);
    app.controller("bookCtrl", function($scope) {
        $scope.book = "The Alchemist";
        $scope.author = "Paulo Coelho";
    });