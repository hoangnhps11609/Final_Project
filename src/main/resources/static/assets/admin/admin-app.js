app=angular.module("admin-app", ["ngRoute"]);
app.controller('datCtrl', function($scope) {
    $scope.today = new Date();
	$scope.hour ="";
});
app.config(function ($routeProvider){
	$routeProvider
	.when("/product", {
		templateUrl: "/assets/admin/product/index.html",
		controller: "product-ctrl"
	}).when("/category", {
		templateUrl: "/assets/admin/category/index.html",
		controller: "category-ctrl"
	}).when("/account", {
		templateUrl: "/assets/admin/account/index.html",
		controller: "account-ctrl"
	}).when("/order", {
		templateUrl: "/assets/admin/order/index.html",
		controller: "order-ctrl"
	}).when("/authorize", {
		templateUrl: "/assets/admin/authority/index.html",
		controller: "authority-ctrl"
	}).when("/unauthozired", {
		templateUrl: "/assets/admin/authority/unauthorized.html",
		controller: "authority-ctrl"
	}).when("/report1", {
		templateUrl: "/assets/admin/report/index.html",
		
	}).otherwise({
		templateUrl: "/assets/admin/background.html"

	})
})