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
	
	
	///Revenue
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
	
	
	
	///Account
	$scope.getAccount6Month = function(){
		$http.get("/rest/reports/getAccount6Month").then(resp => {
			$scope.account6Month = resp.data;
			$scope.type = "Account6Month";
			$scope.title= "Account";
			$scope.message= "New account in 6 months ago";
		});
	}
	
	$scope.showModalAccountByTime = function(){
		$('#AccountByTimeModalCenter').appendTo("body").modal('show');
	}
	
	$scope.getAccountByTime = function() {
		var accountTime = angular.copy($scope.accountByTime);
		$http.get(`/rest/reports/getAccountByTime/${accountTime.from}/${accountTime.to}`).then(resp => {
			$scope.accountTime = resp.data;
			$scope.from = accountTime.from;
			$scope.to = accountTime.to;
			$scope.type = "TimeAccount";
			$scope.title= "Account";
			$scope.message = "";
			$('#AccountByTimeModalCenter').appendTo("body").modal('hide');
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
	
	$scope.getAccountNoOrder = function(){
		$http.get("/rest/reports/getAccountNoOrder").then(resp => {
			$scope.accountNoOrder = resp.data;
			$scope.type = "AccountNoOrder";
			$scope.title= "Account";
			$scope.message= "Number of account: Don't have any Order in 6 months ago";
		});
	}
	
	//Items
	$scope.getItem6Month = function(){
		$http.get("/rest/reports/getItem6Month").then(resp => {
			$scope.item6Month = resp.data;
			$scope.type = "Item6Month";
			$scope.title= "Item";
			$scope.message= "Item Seller in 6 months ago";
		});
	}
	
	$scope.getItemMonth = function(){
		$http.get("/rest/reports/getItemMonth").then(resp => {
			$scope.itemMonth = resp.data;
			$scope.type = "MonthItem";
			$scope.title= "Item";
			$scope.message= "Item This Month";
		});
	}
	
	$scope.showModalItemByTime = function(){
		$('#ItemByTimeModalCenter').appendTo("body").modal('show');
	}
	
	$scope.getItemByTime = function() {
		var itemTime = angular.copy($scope.itemByTime);
		$http.get(`/rest/reports/getItemByTime/${itemTime.from}/${itemTime.to}`).then(resp => {
			$scope.itemTime = resp.data;
			$scope.from = itemTime.from;
			$scope.to = itemTime.to;
			$scope.type = "TimeItem";
			$scope.title= "Item";
			$scope.message = "";
			$('#ItemByTimeModalCenter').appendTo("body").modal('hide');
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
	
	
	//Order
	$scope.getOrderMonth = function(){
		$http.get("/rest/reports/getOrderMonth").then(resp => {
			$scope.orderMonth = resp.data;
			$scope.type = "MonthOrder";
			$scope.title= "Order";
			$scope.message= "Order This Month";
		});
	}
	
	$scope.showModalOrderByTime = function(){
		$('#ItemByOrderModalCenter').appendTo("body").modal('show');
	}
	
	$scope.getOrderByTime = function() {
		var orderTime = angular.copy($scope.orderByTime);
		$http.get(`/rest/reports/getOrderByTime/${orderTime.from}/${orderTime.to}`).then(resp => {
			$scope.orderTime = resp.data;
			$scope.from = orderTime.from;
			$scope.to = orderTime.to;
			$scope.type = "TimeOrder";
			$scope.title= "Order";
			$scope.message = "";
			$('#OrderByTimeModalCenter').appendTo("body").modal('hide');
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
	
	$scope.getOrderCancelledMonth = function(){
		$http.get("/rest/reports/getOrderCancelledYear").then(resp => {
			$scope.orderCancelledYear = resp.data;
			$scope.type = "YearOrderCancelled";
			$scope.title= "Order";
			$scope.message= "Order Cancelled This Year";
		});
	}
	
	
});