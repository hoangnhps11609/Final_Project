app = angular.module("admin-app", ["ngRoute"]);
app.controller('datCtrl', function ($scope) {
	$scope.today = new Date();
	$scope.hour = $scope.today.getHours();
});
app.config(function ($routeProvider) {
//	Swal.fire({
//	  title: 'WELCOME TO ADMIN FASHISHOP!',
//	  text: 'Team 17 FPT Polytechnic',
//	  imageUrl: 'https://caodang.fpt.edu.vn/wp-content/uploads/OK4-1-768x638.png',
//	  imageWidth: 468,
//	  imageHeight: 338,
//	  imageAlt: 'Custom image',
//	})
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
		}).when("/orderstatus", {
			templateUrl: "/assets/admin/orderstatus/index.html",
			controller: "orderstatus-ctrl"
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
			controller: "report-ctrl"
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
});
function list() {
	document.getElementById("homes").style.display = "none";
	document.getElementById("lists").style.display = "block";
}
function home() {
	document.getElementById("homes").style.display = "block";
	document.getElementById("lists").style.display = "none";
}
function setbackground(){
	var dt = new Date();
	var time = dt.getHours()
	if(time<11){
		document.getElementById("bodyh").style.backgroundImage="url(/assets/images/hinhnensang.jpg)";
		document.getElementById("bodyh").style.backgroundSize="100% 100%";
	}
	else{
		if(time<16){
			document.getElementById("bodyh").style.backgroundImage="url(/assets/images/hinhnentrua.jpg)";
			document.getElementById("bodyh").style.backgroundSize="100% 100%";
		}
		else{
			if(time<18){
				document.getElementById("bodyh").style.backgroundImage="url(/assets/images/hinhnenchieu.jpg)";
				document.getElementById("bodyh").style.backgroundSize="100% 100%";
			}
			else{
					document.getElementById("bodyh").style.backgroundImage="url(/assets/images/hinhnentoi.jpg)";
					document.getElementById("bodyh").style.backgroundSize="100% 100%";
			}
		}
	}
}
