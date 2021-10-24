app.controller("report-ctrl", function($scope, $http, $location) {


	$scope.initialize = function() {
		
		//revenue
		$http.get("/rest/reports/revenue").then(resp => {
			$scope.revenue = resp.data;
		});
		
		//sales
		$http.get("/rest/reports/sales").then(resp => {
			$scope.sales = resp.data;
		});
		
		//orders
		$http.get("/rest/reports/orders").then(resp => {
			$scope.orders = resp.data;
		});
		
		//customers
		$http.get("/rest/reports/customers").then(resp => {
			$scope.customers = resp.data;
		});
	}

	$scope.initialize();
	
	$scope.test1 = function(){
		$http.get("/rest/accounts/test").then(resp => {
			$scope.test = resp.data;
		});
	}
	
	
});