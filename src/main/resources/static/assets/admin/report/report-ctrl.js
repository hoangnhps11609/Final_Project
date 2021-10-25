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
		
		$http.get("/rest/reports/getRevenue10Day").then(resp => {
			$scope.revenue10Day = resp.data;
			$scope.type = "10DayRevenue";
			$scope.title= "Revenue";
			$scope.message= "Revenue 10 Days Ago";
		});
	}

	$scope.initialize();
	
	$scope.getRevenue10Day = function(){
		$http.get("/rest/reports/getRevenue10Day").then(resp => {
			$scope.revenue10Day = resp.data;
			$scope.type = "10DayRevenue";
			$scope.title= "Revenue";
			$scope.message= "Revenue 10 Days Ago";
		});
	}
	
	$scope.getRevenueMonth = function(){
		$http.get("/rest/reports/getRevenueMonth").then(resp => {
			$scope.revenueMonth = resp.data;
			$scope.type = "MonthRevenue";
			$scope.title= "Revenue";
			$scope.message= "Revenue This Month";
		});
	}
	
	$scope.getRevenueYear = function(){
		$http.get("/rest/reports/getRevenueYear").then(resp => {
			$scope.revenueYear = resp.data;
			$scope.type = "YearRevenue";
			$scope.title= "Revenue";
			$scope.message= "Revenue This Year";
		});
	}
	
	$scope.showModalRevenueByTime = function(){
		$('#RevenueByTimeModalCenter').appendTo("body").modal('show');
	}
	
	$scope.getRevenueByTime = function() {
		var revenueTime = angular.copy($scope.revenueByTime);
		$http.get(`/rest/reports/getRevenueByTime/${revenueTime.from}/${revenueTime.to}`).then(resp => {
			$scope.revenueTime = resp.data;
			$scope.from = revenueTime.from;
			$scope.to = revenueTime.to;
			$scope.type = "TimeRevenue";
			$scope.title= "Revenue";
			$scope.message = "";
			$('#RevenueByTimeModalCenter').appendTo("body").modal('hide');
		}).catch(error => {
			//alert("Lỗi tìm đơn hàng");
			
			Swal.fire({
			  title: 'Please select a date',
			  showClass: {
			    popup: 'animate__animated animate__fadeInDown'
			  },
			  hideClass: {
			    popup: 'animate__animated animate__fadeOutUp'
			  }
			})
			
			console.log("Error", error);
		});
	}
	
});