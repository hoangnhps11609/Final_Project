app.controller("orderstatus-ctrl", function($scope, $http) {

	 $scope.myFunction = function() {
  var x = document.getElementById("mySelect").value;
  alert(x);
}
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
		
		$scope.findAllWaitingConfirm = function() {
		//load orders
		$http.get("/rest/orders/findAllWaitingConfirm").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
		$scope.findAllConfirmed = function() {
		//load orders
		$http.get("/rest/orders/findAllConfirmed").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
			$scope.findAllShipping = function() {
		//load orders
		$http.get("/rest/orders/findAllShipping").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
			$scope.findAllComplete = function() {
		//load orders
		$http.get("/rest/orders/findAllComplete").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
				$scope.findAllCancelOrder = function() {
		//load orders
		$http.get("/rest/orders/findAllCancelOrder").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}


		$scope.statistic = function () {
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


	//Khởi tạo
	$scope.initialize();

	
	//detail order
	$scope.detail = function(item){
		$http.get(`/rest/orders/myorder/${item.id}`).then(resp => {
			$scope.ODitems = resp.data;
			$scope.orderID = item.id;
			$('.bd-example-modal-lg').appendTo("body").modal('show');
			
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});
	}
	
	$scope.status = function(item){
		$scope.form = angular.copy(item);	
		$('#exampleModalCenter1').appendTo("body").modal('show');
	}
	
	
	$scope.changeStatus = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/orders/${item.id}`, item).then(resp => {
			alert("Cập nhật thành công");
   			 window.location.reload();

			}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
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
	
		$scope.pager2 = {
		page2: 0,
		size2: 2,
		get ODitems() {
			var start = this.page2 * this.size2;
			return $scope.ODitems.slice(start, start + this.size2);
		},
		get count(){
			return Math.ceil(1.0 * $scope.ODitems.length / this.size2);
		}, first(){
			this.page2 = 0;
		}, prev(){
			this.page2--;
			if(this.page2<0){
				this.last();
			}
		}, next(){
			this.page2++;
			if(this.page2 >= this.count){
				this.first();
			}
		}, last(){
			this.page2 = this.count-1;
		}
	}
});