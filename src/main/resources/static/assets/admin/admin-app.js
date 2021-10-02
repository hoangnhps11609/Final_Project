app = angular.module("admin-app", ["ngRoute"]);
app.controller('datCtrl', function ($scope) {
	$scope.today = new Date();
	$scope.hour = $scope.today.getHours();
});
app.config(function ($routeProvider) {
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
		}).when("/voucher", {
			templateUrl: "/assets/admin/voucher/index.html",
			controller: "voucher-ctrl"
		}).when("/report1", {
			templateUrl: "/assets/admin/report/index.html",
		}).when("/blogcategory", {
			templateUrl: "/assets/admin/blogc/index.html",
			controller: "blogcategory-ctrl"
		}).when("/blog", {
			templateUrl: "/assets/admin/blog/index.html",
			controller: "blog-ctrl"
		}).when("/authority", {
			templateUrl: "/assets/admin/authority/index.html",
			controller: "authority-ctrl"
		}).when("/brand", {
			templateUrl: "/assets/admin/brand/index.html",
			controller: "brand-ctrl"
		}).when("/productdetail", {
			templateUrl: "/assets/admin/productdetail/index.html",
			controller: "productdetail-ctrl"
		}).otherwise({
			templateUrl: "/assets/admin/background.html"

		})
})

