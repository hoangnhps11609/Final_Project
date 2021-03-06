app.controller("orderstatus-ctrl", function($scope, $http, $window, $route) {

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
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.message="";
				$scope.message1="";
				$scope.statistic.from = "";
				$scope.items = resp.data;
				$scope.items.forEach(item => {
				})
			}
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
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.message = "Waiting Comfirm Order";
			$scope.message1= "";
			$scope.bg = "bg-primary";
			$scope.items.forEach(item => {
			})
			}
		});
	}
	
		$scope.vForm = false;
	$scope.vTo = false;
	$scope.validateFrom = function(f) {
		if(f == null){
			$scope.vForm = false;
		}else{
			$scope.vForm = true;
			$scope.setFrom = f;
			
		}
	}
	
	$scope.validateTo = function(t) {
		if(t == null){
			$scope.vTo = false;
		}else{
			$scope.vTo = true;
			$scope.setTo = t;
		}
	}
	
		$scope.findAllConfirmed = function() {
		//load orders
		$http.get("/rest/orders/findAllConfirmed").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.message = "Confirmed Order";
			$scope.message1= "";
			$scope.bg = "bg-info";
			$scope.items.forEach(item => {
			})}
		});
	}
	
			$scope.findAllShipping = function() {
		//load orders
		$http.get("/rest/orders/findAllShipping").then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.message = "Shipping Order";
			$scope.message1= "";
			$scope.bg = "bg-warning";
			$scope.items.forEach(item => {
			})}
		});
	}
	
			$scope.findAllComplete = function() {
		//load orders
		$http.get("/rest/orders/findAllComplete").then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.message = "Completed Order";
			$scope.message1= "";
			$scope.bg = "bg-success";
			$scope.items.forEach(item => {
			})}
		});
	}
	
		$scope.findAllCancelOrder = function() {
		//load orders
		$http.get("/rest/orders/findAllCancelOrder").then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.message = "Cancelled Order";
			$scope.message1= "";
			$scope.bg = "bg-danger";
			$scope.items.forEach(item => {
			})}
		});
	}


		$scope.statistic = function () {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/orders/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Find by Keyword: '" + statistic.from + "'";
			$scope.message1 = "";
			$scope.statistic.from = "";
			$scope.bg = "bg-secondary";
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(2)").tab('show');}
		}).catch(error => {
			//alert();
			
			Swal.fire({
			  title: 'Please enter search keyword',
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


	//Kh???i t???o
	$scope.initialize();

	
	//detail order
	$scope.detail = function(item){
		$http.get(`/rest/orders/myorder/${item.id}`).then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.ODitems = resp.data;
			$scope.order = item;
			$('.bd-example-modal-lg').appendTo("body").modal('show');
			}
		}).catch(error => {
			//alert("L???i c???p nh???t s???n ph???m");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Update failure'
			})
			
			console.log("Error", error);

		});
	}
	
	
	$scope.findbyDate = function(){
		$('#findByDateModalCenter').appendTo("body").modal('show');
	}
	
	$scope.status = function(item){
		$scope.form = angular.copy(item);	
		$('#exampleModalCenter1').appendTo("body").modal('show');
	}
	
	
	$scope.changeStatus = function (item) {
		var status = item.status;
		$http.put(`/rest/orders/${item.id}`, item).then(resp => {
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'success',
			  title: 'Update in successfully'
			})
			if(status == 0){
				$scope.findAllConfirmed();
			}else if(status == 1){
				$scope.findAllShipping();
			}else{
				$scope.findAllComplete();
			}
			}).catch(error => {
			//alert("L???i c???p nh???t s???n ph???m");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Update failure'
			})
			
			console.log("Error", error);

		});
	}

	
	$scope.statistic1 = function() {
		var statistic = angular.copy($scope.statistic1);
		$http.get(`/rest/orders/statistic/${statistic.from1}/${statistic.to1}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.from = statistic.from1;
			$scope.to = statistic.to1;
			$scope.message = "";
			$scope.message1 = "Find by Date: From " + statistic.from1 + " To " + statistic.to1;
			$('#findByDateModalCenter').appendTo("body").modal('hide');}
		}).catch(error => {
			//alert("L???i t??m ????n h??ng");
			
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
		size2: 3,
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
	
	$scope.cancelledOrder = function(order){
		Swal.fire({
			title: 'Click "YES" to confirm that you have called the customer!',
			text: "Receiver: " + order.phone + ". Purchaser: " + order.account.phone,
			on: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/orders/cancalledOrder/${order.id}`, order).then(resp => {
					Swal.fire(
						'Successfully!',
						'Updated "' + order.id + '" completed.',
						'success'
					)
					$('.bd-example-modal-lg').appendTo("body").modal('hide');
					$scope.findAllCancelOrder();
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + order.id  +  '" !',
						'error'
					)
				})
			}
		})
	}
});