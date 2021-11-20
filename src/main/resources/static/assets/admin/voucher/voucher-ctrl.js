app.controller("voucher-ctrl", function($scope, $http, $window) {

	$scope.items = [];
	$scope.form = {};

	var input = document.getElementById("myInput");
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.search();
		}
	});

	$scope.initialize = function() {
		//load accounts
		$http.get("/rest/vouchers").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.items = resp.data;
				$scope.count = resp.data.length;
				$scope.message = "";
				$scope.statistic.from = "";
				$scope.to = null;
				$scope.items.forEach(item => {
				})
			}	
		});
	}

	$scope.search = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/vouchers/findby/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.items = resp.data;
				$(".nav a:eq(1)").tab('show');
				$scope.message = "Search by Keyword: " + statistic.from;
				$scope.to = null;
				$scope.statistic.from = "";
				document.getElementById("lists").style.display = "block";
				document.getElementById("homes").style.display = "none";
			}
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

	//Khởi tạo
	$scope.initialize();

	//Xóa form	
	$scope.reset = function() {
		$scope.initialize();
		$scope.form = {
		};
		$scope.isEdit = null;
		$scope.message1 = "";
		$scope.message2 = "";
		$scope.message3 = "";
		$scope.message4 = "";
		$scope.vValue = false;
		
		
		
	}
	
	
	$scope.vValue = false;
	$scope.validateValue = function(v) {
	const number = /^\d+$/;
		if(v == null){
			$scope.message2 = "Value not allow null";
			$scope.vValue = false;
		}else if(!v.match(number)){
			$scope.message2 = "Only number";
			$scope.vValue = false;
		}else{
			$scope.vValue = true;
			$scope.message2 = "";
			
		}
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
		$scope.isEdit = "true";
		$scope.vValue = true;
	}
	
	

	//Thêm account mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var value = item.value;
		Swal.fire({
			title: 'Confirm adding voucher with value "$' + value + '" to the voucher list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/vouchers`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Added voucher with value "$' + value + '" to voucher list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show')
				}).catch(error => {

					Swal.fire(
						'Create Failure!',
						'Can not add voucher with value "$' + value + '" !',
						'error'
					)

					console.log("Error", error);
				});
			}
		})
	}

	//update sản phẩm mới
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var name = item.name;

		Swal.fire({
			title: 'Confirm edit information "' + name + '" !',
			text: "New information will be saved to the voucher list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/vouchers/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Updated "' + name + '" to voucher list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + name + '" !',
						'error'
					)

					console.log("Error", error);

				});
			}
		})
	}


	//Xóa sản phẩm mới
	$scope.delete = function(item) {
		var name = item.name;
		Swal.fire({
			title: 'Are you sure delete "' + name + '"?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/rest/vouchers/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.initialize();
					$scope.reset();
					Swal.fire(
						'Deleted!',
						'Voucher "' + name + '" has been deleted.',
						'success'
					)
				}).catch(error => {
					Swal.fire(
						'Delete Failure!',
						'Can not delete "' + name + '" !',
						'error'
					)

					console.log("Error", error);

				});

			}
		})

	}


	$scope.NewVoucher = function() {
		$http.get("/rest/vouchers/getnewvoucher").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.items = resp.data;
				$scope.message = "Find by: New Voucher" ;
			}
		});
	}
	
	$scope.UsedVoucher = function() {
		$http.get("/rest/vouchers/getusedvoucher").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.items = resp.data;
				$scope.message = "Find by: Used Voucher" ;
			}
		});
	}


	

	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		}, first() {
			this.page = 0;
		}, prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		}, next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		}, last() {
			this.page = this.count - 1;
		}
	}


	$scope.happyBirthdayVoucher = function(){
		$http.get("/rest/vouchers/sendVoucherHPBD").then(resp => {
			$scope.sendHPBD = "false";
			$scope.initialize();
			Swal.fire('Send Successfully!');
		})
	}

});
