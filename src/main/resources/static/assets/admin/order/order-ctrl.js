app.controller("order-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	
	$scope.ODitems = [];

	$scope.initialize = function() {
		//load orders
		$http.get("/rest/orders").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
			var input = document.getElementById("myInput");
		input.addEventListener("keyup", function(event) {
  		if (event.keyCode === 13) {
   			event.preventDefault();
   			$scope.statistic();
   
  }
});	

	//Khởi tạo
	$scope.initialize();

	
	//detail order
	$scope.detail = function(item){
		$http.get(`/rest/orders/myorder/${item.id}`).then(resp => {
			$scope.ODitems = resp.data;
			$scope.orderID = item.id;
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});
	}
	
			$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/orders/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav-tabs a:eq(2)").tab('show');
		}).catch(error => {
			alert();
			console.log("Error", error);
		});
	}
	
	$scope.statistic1 = function() {
		var statistic = angular.copy($scope.statistic1);
		$http.get(`/rest/orders/statistic/${statistic.from1}/${statistic.to1}`).then(resp => {
			$scope.items = resp.data;
			$(".nav-tabs a:eq(2)").tab('show');
		}).catch(error => {
			alert("Lỗi tìm đơn hàng");
			console.log("Error", error);
		});
	}
	
	

	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		}, first(){
			this.page = 0;
		}, prev(){
			this.page--;
			if(this.page<0){
				this.last();
			}
		}, next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		}, last(){
			this.page = this.count-1;
		}
	}
});